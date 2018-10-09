package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleOne;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.AttributeSet;

import com.aserbao.aserbaosandroid.AUtils.utils.BufferUtil;
import com.aserbao.aserbaosandroid.AUtils.utils.ShaderUtils;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * description:
 * Created by aserbao on 2018/1/26.
 */


public class CameraOneView extends GLSurfaceView implements SurfaceTexture.OnFrameAvailableListener{
    public CameraOneView(Context context) {
        this(context, null);
    }

    public CameraOneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(new CameraRenderer(context));
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        requestRender();
    }

    class CameraRenderer implements Renderer{
        private Context mContext;
        private CameraManeger mCameraManeger;
        private SurfaceTexture mCameraTexture;

        private int mProgram;

        private int uPosHandle;
        private int aTexHandle;
        private int mMVPMatrixHandle;

        private float[] mProjectMatrix = new float[16];
        private float[] mCameraMatrix  = new float[16];
        private float[] mMVPMatrix     = new float[16];
        private float[] mTempMatrix     = new float[16];

        private float[] mPosCoordinate = {
                -1, -1,
                -1, 1,
                1, -1,
                1, 1};
        private float[] mTexCoordinate = {
                0, 1,
                1, 1,
                0, 0,
                1, 0};

        private FloatBuffer mPosBuffer;
        private FloatBuffer mTexBuffer;

        public CameraRenderer(Context context) {
            mContext = context;
            Matrix.setIdentityM(mProjectMatrix, 0);
            Matrix.setIdentityM(mCameraMatrix, 0);
            Matrix.setIdentityM(mMVPMatrix, 0);
            Matrix.setIdentityM(mTempMatrix, 0);

            mCameraManeger = new CameraManeger();
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
            mProgram = ShaderUtils.createProgram(mContext, "vertex_texture.glsl", "fragment_texture.glsl");
            GLES20.glUseProgram(mProgram);//激活OpenGl程序
            createAndBindVideoTexture();
            mCameraManeger.OpenCamera(mCameraTexture);//为相机设置接收数据的SurfaceTexture
            uPosHandle           = GLES20.glGetAttribLocation (mProgram, "position");
            aTexHandle           = GLES20.glGetAttribLocation (mProgram, "inputTextureCoordinate");
            mMVPMatrixHandle    = GLES20.glGetUniformLocation(mProgram, "textureTransform");

            mPosBuffer = BufferUtil.convertToFloatBuffer(mPosCoordinate);
            mTexBuffer = BufferUtil.convertToFloatBuffer(mTexCoordinate);

            GLES20.glVertexAttribPointer(uPosHandle, 2, GLES20.GL_FLOAT, false, 0, mPosBuffer);
            GLES20.glVertexAttribPointer(aTexHandle, 2, GLES20.GL_FLOAT, false, 0, mTexBuffer);

            GLES20.glEnableVertexAttribArray(uPosHandle);
            GLES20.glEnableVertexAttribArray(aTexHandle);
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
            mCameraTexture.updateTexImage();//通过此方法更新接收到的预览数据
//            mCameraTexture.getTransformMatrix(mTempMatrix);//获取到图像数据流的坐标变换矩阵
//            Matrix.multiplyMM(mTempMatrix, 0, mTempMatrix, 0, mMVPMatrix, 0);
            GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mPosCoordinate.length / 2);
        }
        /**创建显示的texture*/
        private void createAndBindVideoTexture() {
            int[] texture = new int[1];

            GLES20.glGenTextures(1, texture, 0);//生成一个OpenGl纹理
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);//申请纹理存储区域并设置相关参数
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

            mCameraTexture = new SurfaceTexture(texture[0]);//以上面OpenGl生成的纹理函数参数创建SurfaceTexture,SurfaceTexture接收的数据将传入该纹理
            mCameraTexture.setOnFrameAvailableListener(CameraOneView.this);//设置SurfaceTexture的回调，通过摄像头预览数据已更新
        }
    }

}
