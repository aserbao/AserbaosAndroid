package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aserbao.aserbaosandroid.AUtils.AssetsUtils;
import com.aserbao.aserbaosandroid.AUtils.BufferUtil;
import com.aserbao.aserbaosandroid.AUtils.ShaderUtils;
import com.aserbao.aserbaosandroid.R;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

public class CameraGlSurfaceShowActivity extends AppCompatActivity implements SurfaceTexture.OnFrameAvailableListener{

    public static Camera camera;

    @BindView(R.id.camera_glsurface_view)
    GLSurfaceView mCameraGlsurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gl_surface_show);
        ButterKnife.bind(this);
        mCameraGlsurfaceView.setEGLContextClientVersion(2);//在setRenderer()方法前调用此方法
        mCameraGlsurfaceView.setRenderer(new MyRender());
        mCameraGlsurfaceView. setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @OnClick(R.id.btn_gl_surface_view_animator)
    public void onViewClicked() {
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        mCameraGlsurfaceView.requestRender();
    }

    public class MyRender implements GLSurfaceView.Renderer {
        private final String vertexShaderCode = "uniform mat4 textureTransform;\n" +
                "attribute vec2 inputTextureCoordinate;\n" +
                "attribute vec4 position;            \n" +//NDK坐标点
                "varying   vec2 textureCoordinate; \n" +//纹理坐标点变换后输出
                "\n" +
                " void main() {\n" +
                "     gl_Position = position;\n" +
                "     textureCoordinate = inputTextureCoordinate;\n" +
                " }";

        private final String fragmentShaderCode = "#extension GL_OES_EGL_image_external : require\n" +
                "precision mediump float;\n" +
                "uniform samplerExternalOES videoTex;\n" +
                "varying vec2 textureCoordinate;\n" +
                "\n" +
                "void main() {\n" +
                "    vec4 tc = texture2D(videoTex, textureCoordinate);\n" +
//                "    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n" +
//                "    gl_FragColor = vec4(color,color,color,1.0);\n" +
                "    gl_FragColor = vec4(tc.r,tc.g,tc.b,1.0);\n" +
                "}\n";
        private FloatBuffer mPosBuffer;
        private FloatBuffer mTexBuffer;
        private float[] mPosCoordinate = {-1, -1,-1, 1,1, -1,1, 1};
        private float[] mTexCoordinate = {0, 1,1, 1,0, 0,1, 0};
        public int mProgram;

        public MyRender() {
            Matrix.setIdentityM(mProjectMatrix, 0);
            Matrix.setIdentityM(mCameraMatrix, 0);
            Matrix.setIdentityM(mMVPMatrix, 0);
            Matrix.setIdentityM(mTempMatrix, 0);
        }

        private  int loadShader(int type, String shaderCode){
            int shader = GLES20.glCreateShader(type);
            // 添加上面编写的着色器代码并编译它
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }
        private void creatProgram(){
//            String vertexSource = AssetsUtils.read(CameraGlSurfaceShowActivity.this, "vertex_texture.glsl");
//            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
//            String fragmentSource = AssetsUtils.read(CameraGlSurfaceShowActivity.this, "fragment_texture.glsl");
//            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
            // 创建空的OpenGL ES程序
            mProgram = GLES20.glCreateProgram();

            // 添加顶点着色器到程序中
            GLES20.glAttachShader(mProgram, vertexShader);

            // 添加片段着色器到程序中
            GLES20.glAttachShader(mProgram, fragmentShader);

            // 创建OpenGL ES程序可执行文件
            GLES20.glLinkProgram(mProgram);

            // 释放shader资源
            GLES20.glDeleteShader(vertexShader );
            GLES20.glDeleteShader(fragmentShader);
        }
        private FloatBuffer convertToFloatBuffer(float[] buffer){
            FloatBuffer fb = ByteBuffer.allocateDirect(buffer.length * 4)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer();
            fb.put(buffer);
            fb.position(0);
            return fb;
        }
        private int uPosHandle;
        private int aTexHandle;
        private int mMVPMatrixHandle;
        private float[] mProjectMatrix = new float[16];
        private float[] mCameraMatrix  = new float[16];
        private float[] mMVPMatrix     = new float[16];
        private float[] mTempMatrix     = new float[16];
        //添加程序到ES环境中
        private void activeProgram(){
            // 将程序添加到OpenGL ES环境
            GLES20.glUseProgram(mProgram);

            mSurfaceTexture.setOnFrameAvailableListener(CameraGlSurfaceShowActivity.this);
            // 获取顶点着色器的位置的句柄
            uPosHandle           = GLES20.glGetAttribLocation (mProgram, "position");
            aTexHandle           = GLES20.glGetAttribLocation (mProgram, "inputTextureCoordinate");
            mMVPMatrixHandle    = GLES20.glGetUniformLocation(mProgram, "textureTransform");

            mPosBuffer = convertToFloatBuffer(mPosCoordinate);
            mTexBuffer = convertToFloatBuffer(mTexCoordinate);

            GLES20.glVertexAttribPointer(uPosHandle, 2, GLES20.GL_FLOAT, false, 0, mPosBuffer);
            GLES20.glVertexAttribPointer(aTexHandle, 2, GLES20.GL_FLOAT, false, 0, mTexBuffer);

            // 启用顶点位置的句柄
            GLES20.glEnableVertexAttribArray(uPosHandle);
            GLES20.glEnableVertexAttribArray(aTexHandle);
        }

        public SurfaceTexture mSurfaceTexture;
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            mSurfaceTexture = new SurfaceTexture(createOESTextureObject());
            creatProgram();
//            mProgram = ShaderUtils.createProgram(CameraGlSurfaceShowActivity.this, "vertex_texture.glsl", "fragment_texture.glsl");
            camera = Camera.open(1);
            camera.setDisplayOrientation(90);
            try {
                camera.setPreviewTexture(mSurfaceTexture);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
            activeProgram();

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float)width/height;
            Matrix.orthoM(mProjectMatrix,0,-1,1,-ratio,ratio,1,7);// 3和7代表远近视点与眼睛的距离，非坐标点
            Matrix.setLookAtM(mCameraMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);// 3代表眼睛的坐标点
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mCameraMatrix, 0);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            mSurfaceTexture.updateTexImage();
            GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mPosCoordinate.length / 2);
        }
    }
    public static int createOESTextureObject() {
        int[] tex = new int[1];
        //生成一个纹理
        GLES20.glGenTextures(1, tex, 0);
        //将此纹理绑定到外部纹理上
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, tex[0]);
        //设置纹理过滤参数
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
        return tex[0];
    }
}
