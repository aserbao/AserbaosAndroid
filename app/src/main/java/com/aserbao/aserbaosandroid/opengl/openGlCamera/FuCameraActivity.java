package com.aserbao.aserbaosandroid.opengl.openGlCamera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.CameraUtils;
import com.aserbao.aserbaosandroid.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuCameraActivity extends AppCompatActivity implements SurfaceTexture.OnFrameAvailableListener, Camera.PreviewCallback {
    private static final String TAG = "FuCameraActivity";
    int currentCameraType = Camera.CameraInfo.CAMERA_FACING_FRONT,cameraWidth = 1280,cameraHeight = 720;
    final int PREVIEW_BUFFER_COUNT = 3;
    byte[] mCameraNV21Byte;
    byte[][] previewCallbackBuffer;
    boolean isInPause = false,isNeedSwitchCameraSurfaceTexture = true;
    Camera mCamera;
    Handler mMainHandler;
    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;
    public GLRenderer mGLRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_camera);
        ButterKnife.bind(this);
        mMainHandler = new MainHandler(this);

        mGlSurfaceView.setEGLContextClientVersion(2);
        mGLRenderer = new GLRenderer();
        mGlSurfaceView.setRenderer(mGLRenderer);
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openCamera(currentCameraType, cameraWidth, cameraHeight);
        mGlSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void openCamera(int cameraType, int desiredWidth, int desiredHeight) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        int cameraId = 0;
        int numCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numCameras; i++) {
            Camera.getCameraInfo(i, info);
            if (info.facing == cameraType) {
                cameraId = i;
                mCamera = Camera.open(i);
                currentCameraType = cameraType;
                break;
            }
        }
        if(mCamera == null){
            Toast.makeText(this, "相机打开失败", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        CameraUtils.setCameraDisplayOrientation(this, cameraId, mCamera);
        Camera.Parameters parameters = mCamera.getParameters();

        /**
         * 设置对焦，会影响camera吞吐速率
         */
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO))
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);

        /**
         * 设置fps
         * */
        int[] closetFramerate = CameraUtils.closetFramerate(parameters, 30);
        Log.e(TAG, "closet framerate min " + closetFramerate[0] + " max " + closetFramerate[1]);
        parameters.setPreviewFpsRange(closetFramerate[0], closetFramerate[1]);

        CameraUtils.choosePreviewSize(parameters, desiredWidth, desiredHeight);
        mCamera.setParameters(parameters);
    }
    
    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        mCameraNV21Byte = isInPause ? null : data;
        mCamera.addCallbackBuffer(data);
    }

    class GLRenderer implements GLSurfaceView.Renderer{
        int mCameraTextureId = 1;
        SurfaceTexture mCameraSurfaceTexture;
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            if (isNeedSwitchCameraSurfaceTexture) {
                switchCameraSurfaceTexture();
            }
            float[] mtx = new float[16];
            if (mCameraSurfaceTexture != null) {
                try {
                    mCameraSurfaceTexture.updateTexImage();
                    mCameraSurfaceTexture.getTransformMatrix(mtx);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else throw new RuntimeException("HOW COULD IT HAPPEN!!! mCameraSurfaceTexture is null!!!");

            if (mCameraNV21Byte == null || mCameraNV21Byte.length == 0) {
                Log.e(TAG, "camera nv21 bytes null");
                mGlSurfaceView.requestRender();
                mGlSurfaceView.requestRender();
                return;
            }

            if (!isInPause) mGlSurfaceView.requestRender();
        }
        public void switchCameraSurfaceTexture(){
            isNeedSwitchCameraSurfaceTexture = false;
            mCameraSurfaceTexture = new SurfaceTexture(mCameraTextureId);
            mMainHandler.sendMessage(mMainHandler.obtainMessage(
                    MainHandler.HANDLE_CAMERA_START_PREVIEW,
                    mCameraSurfaceTexture));
        }
    }


    static class MainHandler extends Handler {

        static final int HANDLE_CAMERA_START_PREVIEW = 1;

        private WeakReference<FuCameraActivity> mActivityWeakReference;

        MainHandler(FuCameraActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FuCameraActivity activity = mActivityWeakReference.get();
            switch (msg.what) {
                case HANDLE_CAMERA_START_PREVIEW:
                    activity.handleCameraStartPreview((SurfaceTexture) msg.obj);
                    break;
            }
        }
    }
    private void handleCameraStartPreview(SurfaceTexture surfaceTexture) {
        if (previewCallbackBuffer == null) {
            Log.e(TAG, "allocate preview callback buffer");
            previewCallbackBuffer = new byte[PREVIEW_BUFFER_COUNT][cameraWidth * cameraHeight * 3 / 2];
        }
        mCamera.setPreviewCallbackWithBuffer(this);
        for (int i = 0; i < PREVIEW_BUFFER_COUNT; i++)
            mCamera.addCallbackBuffer(previewCallbackBuffer[i]);
        try {
            mCamera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceTexture.setOnFrameAvailableListener(this);
        mCamera.startPreview();
    }

}
