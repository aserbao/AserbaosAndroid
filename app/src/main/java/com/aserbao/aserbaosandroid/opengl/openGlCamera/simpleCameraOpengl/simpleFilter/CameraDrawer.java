package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter;

import android.content.res.Resources;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter.filters.AFilter;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter.utils.CameraFilter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * description:管理图像绘制的类
 * 主要用于管理各种滤镜、画面旋转、视频编码录制等
 * Created by aserbao on 2018/1/29.
 */


public class CameraDrawer implements GLSurfaceView.Renderer{
    /**后台绘制的filter*/
    private final AFilter drawFilter;
    private int textureID;
    private SurfaceTexture mSurfaceTextrue;
    /**预览数据的宽高*/
    private int mPreviewWidth=0,mPreviewHeight=0;
    public CameraDrawer(Resources resources) {
        drawFilter = new CameraFilter(resources);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        textureID = createTextureID();
        mSurfaceTextrue = new SurfaceTexture(textureID);
        drawFilter.create();
        drawFilter.setTextureId(textureID);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    /**设置预览效果的size*/
    public void setPreviewSize(int width,int height){
        if (mPreviewWidth != width || mPreviewHeight != height){
            mPreviewWidth = width;
            mPreviewHeight = height;
        }
    }
    /**根据摄像头设置纹理映射坐标*/
    public void setCameraId(int id) {
        drawFilter.setFlag(id);
    }

    /**创建显示的texture*/
    private int createTextureID() {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        return texture[0];
    }
    public SurfaceTexture getTexture() {
        return mSurfaceTextrue;
    }
}
