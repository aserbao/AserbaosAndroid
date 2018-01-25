package com.aserbao.aserbaosandroid.opengl.openGlCamera;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.lang.reflect.Method;

public class SurfacePreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = SurfacePreview.class.getSimpleName();
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Camera.Parameters mParameters;

    public SurfacePreview(Context context) {
        super(context);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated() is called");
        try {
            // Open the Camera in preview mode
            mCamera = Camera.open(1);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.d(TAG, "surfaceChanged() is called");
        try {
//            mCamera.startPreview();
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if(success){
                        initCamera();
                        camera.cancelAutoFocus();
                    }
                }
            });
        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    private void initCamera() {
        mParameters = mCamera.getParameters();
        mParameters.setPictureFormat(PixelFormat.JPEG);
        mParameters.setPictureSize(1080,1920);
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        setDispaly(mParameters, mCamera);
        mCamera.setParameters(mParameters);
        mCamera.startPreview();
        mCamera.cancelAutoFocus();

    }
    private void setDispaly(Camera.Parameters parameters,Camera camera) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8){
            setDisplayOrientation(camera,90);
        } else {
            parameters.setRotation(90);
        }
    }

    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try{
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[]{int.class});
            if(downPolymorphic!=null) {
                downPolymorphic.invoke(camera, new Object[]{i});
            }
        }
        catch(Exception e){
            Log.e(TAG, "image error");
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        Log.d(TAG, "surfaceDestroyed() is called");
    }

    public void takePicture(Camera.PictureCallback imageCallback) {
        mCamera.takePicture(null, null, imageCallback);
    }
}