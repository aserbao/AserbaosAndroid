package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.*
import android.opengl.GLES11Ext
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aserbao.aserbaosandroid.databinding.ActivityCamera2GlsurfaceViewBinding
import com.getremark.base.kotlin_ext.runOnMainThread
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.activity_camera2_glsurface_view.*
import kotlinx.android.synthetic.main.activity_camera2_glsurface_view.view.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Camera2GlsurfaceViewActivity : AppCompatActivity(), SurfaceTexture.OnFrameAvailableListener {
    lateinit var bindRoot: View
    var mSurfaceTexture: SurfaceTexture? = null
    private var camera_status = 1
    var mRenderer: MyRender? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflate = ActivityCamera2GlsurfaceViewBinding.inflate(LayoutInflater.from(this))
        bindRoot = inflate.root
        setContentView(bindRoot)
        initView()
        initViewEvent()
    }

    private fun initViewEvent() {
        glSurfaceView.singleClick {
            if(cameraId.equals(mBackCameraId)){
                cameraId = mFrontCameraId;
            }else{
                cameraId = mBackCameraId;
            }
            mRenderer?.switchCamera()
        }
    }

    private fun initView() {
        bindRoot.apply {
            glSurfaceView!!.setEGLContextClientVersion(2) //在setRenderer()方法前调用此方法
            mRenderer = MyRender()
            glSurfaceView!!.setRenderer(mRenderer)
            glSurfaceView!!.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
        }
    }



    override fun onFrameAvailable(surfaceTexture: SurfaceTexture) {
        glSurfaceView.requestRender()
    }


    inner class MyRender : GLSurfaceView.Renderer {
        private val vertexShaderCode = """uniform mat4 textureTransform;
                uniform mat4 uMVPMatrix;
                attribute vec2 inputTextureCoordinate;
                attribute vec4 position;
                varying   vec2 textureCoordinate;
                
                 void main() {
                     gl_Position = uMVPMatrix * position;
                     textureCoordinate = inputTextureCoordinate;
                 }"""
        private val fragmentShaderCode = """#extension GL_OES_EGL_image_external : require
                precision mediump float;
                uniform samplerExternalOES videoTex;
                varying vec2 textureCoordinate;
                
                void main() {
                    vec4 tc = texture2D(videoTex, textureCoordinate);
                    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;
                    gl_FragColor = vec4(color,color,color,1.0);
                }
                """
        private var mPosBuffer: FloatBuffer? = null
        private var mTexBuffer: FloatBuffer? = null
        private val mPosCoordinate = floatArrayOf(-1f, -1f, -1f, 1f, 1f, -1f, 1f, 1f)
        private val mTexCoordinateBackRight = floatArrayOf(1f, 1f, 0f, 1f, 1f, 0f, 0f, 0f) //顺时针转90并沿Y轴翻转  后摄像头正确，前摄像头上下颠倒
        private val mTexCoordinateForntRight = floatArrayOf(0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f) //顺时针旋转90  后摄像头上下颠倒了，前摄像头正确
        var mProgram = 0
        var mBoolean = false
        private fun loadShader(type: Int, shaderCode: String): Int {
            val shader = GLES20.glCreateShader(type)
            // 添加上面编写的着色器代码并编译它
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }

        private fun creatProgram() {
            //通常做法
//            String vertexSource = AssetsUtils.read(CameraGlSurfaceShowActivity.this, "vertex_texture.glsl");
//            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
//            String fragmentSource = AssetsUtils.read(CameraGlSurfaceShowActivity.this, "fragment_texture.glsl");
//            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
            val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
            val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
            // 创建空的OpenGL ES程序
            mProgram = GLES20.glCreateProgram()

            // 添加顶点着色器到程序中
            GLES20.glAttachShader(mProgram, vertexShader)

            // 添加片段着色器到程序中
            GLES20.glAttachShader(mProgram, fragmentShader)

            // 创建OpenGL ES程序可执行文件
            GLES20.glLinkProgram(mProgram)

            // 释放shader资源
            GLES20.glDeleteShader(vertexShader)
            GLES20.glDeleteShader(fragmentShader)
        }

        private fun convertToFloatBuffer(buffer: FloatArray): FloatBuffer {
            val fb = ByteBuffer.allocateDirect(buffer.size * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
            fb.put(buffer)
            fb.position(0)
            return fb
        }

        private var uPosHandle = 0
        private var aTexHandle = 0
        private var mMVPMatrixHandle = 0
        private var muMVPMatrixLHandle = 0

        private val mProjectMatrix = FloatArray(16)
        private val mCameraMatrix = FloatArray(16)
        private val mMVPMatrix = FloatArray(16)
        private val mRotationMatrix = FloatArray(16)
        private val mTempMatrix = FloatArray(16)

        //添加程序到ES环境中
        private fun activeProgram() {
            // 将程序添加到OpenGL ES环境
            GLES20.glUseProgram(mProgram)
            mSurfaceTexture!!.setOnFrameAvailableListener(this@Camera2GlsurfaceViewActivity)
            // 获取顶点着色器的位置的句柄
            uPosHandle = GLES20.glGetAttribLocation(mProgram, "position")
            aTexHandle = GLES20.glGetAttribLocation(mProgram, "inputTextureCoordinate")
            mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "textureTransform")
            muMVPMatrixLHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")

            mPosBuffer = convertToFloatBuffer(mPosCoordinate)
            mTexBuffer = if (cameraId.equals(mBackCameraId)) {
                convertToFloatBuffer(mTexCoordinateBackRight)
            } else {
                convertToFloatBuffer(mTexCoordinateForntRight)
            }
            GLES20.glVertexAttribPointer(uPosHandle, 2, GLES20.GL_FLOAT, false, 0, mPosBuffer)
            GLES20.glVertexAttribPointer(aTexHandle, 2, GLES20.GL_FLOAT, false, 0, mTexBuffer)

            // 启用顶点位置的句柄
            GLES20.glEnableVertexAttribArray(uPosHandle)
            GLES20.glEnableVertexAttribArray(aTexHandle)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
            mSurfaceTexture = SurfaceTexture(createOESTextureObject())
            switchCamera()
        }

        fun switchCamera(){
            cameraDevice?.close()
            creatProgram()
            //            mProgram = ShaderUtils.createProgram(CameraGlSurfaceShowActivity.this, "vertex_texture.glsl", "fragment_texture.glsl");
            runOnMainThread {
                openCamera2()
            }
            activeProgram()
        }


        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            GLES20.glViewport(0, 0, width, height)
            Matrix.scaleM(mMVPMatrix, 0, 1f, -1f, 1f)
            val ratio = width.toFloat() / height
            Matrix.orthoM(mProjectMatrix, 0, -1f, 1f, -ratio, ratio, 1f, 7f) // 3和7代表远近视点与眼睛的距离，非坐标点
            Matrix.setLookAtM(mCameraMatrix, 0, 0f, 0f, 3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f) // 3代表眼睛的坐标点
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mCameraMatrix, 0)
        }

        override fun onDrawFrame(gl: GL10) {
            if (mBoolean) {
                activeProgram()
                mBoolean = false
            }
            if (mSurfaceTexture != null) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
                mSurfaceTexture!!.updateTexImage()
                GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0)
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mPosCoordinate.size / 2)
            }
        }
        init {
            Matrix.setIdentityM(mProjectMatrix, 0)
            Matrix.setIdentityM(mCameraMatrix, 0)
            Matrix.setIdentityM(mMVPMatrix, 0)
            Matrix.setIdentityM(mTempMatrix, 0)
        }
    }

    private val mFrontCameraId = "1"
    private val mBackCameraId = "0"
    private var cameraId = mBackCameraId
    fun isForntCamera():Boolean{
        return cameraId.equals(mFrontCameraId)
    }

    var cameraDevice:CameraDevice ?= null
    private var captureRequest: CaptureRequest? = null
    fun openCamera2() {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show()
                return
            }
            manager.openCamera(cameraId, mStateCall,null)
        } catch (e : CameraAccessException) {
            e.printStackTrace();
        }
    }

    var mStateCall = object:CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera;
            startPreview()
        }
        override fun onDisconnected(camera: CameraDevice) {}
        override fun onError(camera: CameraDevice, error: Int) {}
        override fun onClosed(camera: CameraDevice) {
            super.onClosed(camera)
            cameraDevice  = null
        }
    }

    fun startPreview() {
        mSurfaceTexture?.apply {
            var surface = Surface(this)
            setDefaultBufferSize(1920,1080)
            var cameraCaptureRequest: CaptureRequest.Builder? = null
            cameraCaptureRequest = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            cameraCaptureRequest.addTarget(surface)
            try {
                cameraDevice!!.createCaptureSession(Arrays.asList(surface), object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        try {
                            captureRequest = cameraCaptureRequest.build()
                            session.setRepeatingRequest(captureRequest, object : CameraCaptureSession.CaptureCallback() {
                                override fun onCaptureStarted(session: CameraCaptureSession, request: CaptureRequest, timestamp: Long, frameNumber: Long) {
                                    super.onCaptureStarted(session, request, timestamp, frameNumber)
                                }
                            }, null)
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {}
                }, null)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }


    companion object {
        var camera: Camera? = null

        /**
         * 创建外部纹理对象
         */
        fun createOESTextureObject(): Int {
            val tex = IntArray(1)
            //生成一个纹理
            GLES20.glGenTextures(1, tex, 0)
            //将此纹理绑定到外部纹理上
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, tex[0])
            //设置纹理过滤参数
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST.toFloat())
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR.toFloat())
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE.toFloat())
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE.toFloat())
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0)
            return tex[0]
        }
    }

}