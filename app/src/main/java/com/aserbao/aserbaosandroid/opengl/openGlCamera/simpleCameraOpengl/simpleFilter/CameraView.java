package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.hardware.Camera.open;

/**
 * description:
 * Created by aserbao on 2018/1/29.
 */


public class CameraView extends GLSurfaceView implements GLSurfaceView.Renderer,SurfaceTexture.OnFrameAvailableListener{
    private int cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    public CameraController mCamera;
    public CameraDrawer mCameraDrawer;
    private int dataWidth=0,dataHeight=0;

    public CameraView(Context context) {
        this(context,null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置版本
        setRenderer(this);//设置Renderer
        setRenderMode(RENDERMODE_WHEN_DIRTY);//主动调用渲染
        mCamera = new CameraController();
        mCameraDrawer = new CameraDrawer(getResources());
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.requestRender();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCameraDrawer.onSurfaceCreated(gl,config);
        openCamera(cameraId);
        mCameraDrawer.setPreviewSize(dataWidth,dataHeight);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mCameraDrawer.onSurfaceChanged(gl,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mCameraDrawer.onDrawFrame(gl);
    }

    private void openCamera(int cameraId){
        mCamera.close();
        mCamera.open(cameraId);
        mCameraDrawer.setCameraId(cameraId);
        final Point previewSize=mCamera.getPreviewSize();
        dataWidth=previewSize.x;
        dataHeight=previewSize.y;
        SurfaceTexture texture = mCameraDrawer.getTexture();
        texture.setOnFrameAvailableListener(this);
        mCamera.setPreviewTexture(texture);
        mCamera.preview();
    }
    public void switchCamera(){
        cameraId = cameraId==0?1:0;
        open(cameraId);
    }

}
