package com.aserbao.aserbaosandroid.opengl.videoandcamera

import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.opengl.GLES11Ext
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.view.Surface
import com.aserbao.aserbaosandroid.opengl.videoandcamera.program.VideoProgram
import com.example.base.utils.log.ALogUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/*
* 作用：
* @author aserbao
* @date: on 2020/8/14
* @project: AserbaosAndroid
* @package: com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.doubleshow
*/
class VideoandCameraGlRender(var context: Context) {
    private val TAG = "CameraPreviewRender"

   lateinit var mVideoProgram: VideoProgram
    var mSurfaceTexture: SurfaceTexture? = null
    init {
        mVideoProgram= VideoProgram()
    }


    fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        var textureId = createOESTextureObject()
        mSurfaceTexture = SurfaceTexture(textureId)
        mVideoProgram.textureId = textureId
        mVideoProgram.onSurfaceCreated(gl,config)
        ALogUtils.d(TAG, " onSurfaceCreated")
    }


    fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        mVideoProgram.onSurfaceChanged(gl,width,height)
    }

    fun onDrawFrame(gl: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        if (mSurfaceTexture != null) {
            mVideoProgram.onDrawFrame(gl)
            mSurfaceTexture!!.updateTexImage()
        }
    }


    companion object {
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

    fun getSurfaceTexture():SurfaceTexture?{
        return mSurfaceTexture
    }

}