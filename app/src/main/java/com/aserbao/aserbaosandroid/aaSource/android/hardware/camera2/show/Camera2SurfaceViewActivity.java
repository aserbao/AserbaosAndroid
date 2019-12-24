package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera2SurfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final String TAG = "Camera2SurfaceViewActiv";
    @BindView(R.id.mSurface)
    SurfaceView mSurfaceView;
    public SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_camera);
        ButterKnife.bind(this);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    public void detailCamera(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        CameraManager cameraManager1 = getSystemService(CameraManager.class);
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            for (String s : cameraIdList) {
                Log.e(TAG, "detailCamera: " + s );
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
