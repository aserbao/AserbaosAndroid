package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera2SimpleShowSVActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final String TAG = "Camera2SurfaceViewActiv";
    @BindView(R.id.mSurface)
    SurfaceView mSurfaceView;
    public SurfaceHolder mHolder;
    private CaptureRequest captureRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_camera);
        ButterKnife.bind(this);
        mSurfaceView = ((SurfaceView) findViewById(R.id.mSurface));
        SurfaceHolder mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        openCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    /**
     * 打开相机权限
     */
    public void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics("0");
        //CameraManager cameraManager1 = getSystemService(CameraManager.class);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show();
                return;
            }
            manager.openCamera("0", new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        Surface surface = mHolder.getSurface();
                        List<Surface> mSurfaces = new ArrayList<>();
                        mSurfaces.add(surface);
                        try {
                            camera.createCaptureSession(mSurfaces, new CameraCaptureSession.StateCallback() {
                                @Override
                                public void onConfigured(@NonNull CameraCaptureSession session) {
                                    CaptureRequest.Builder cameraCaptureRequest = null;
                                    try {
                                        cameraCaptureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                                        cameraCaptureRequest.addTarget(mHolder.getSurface());
                                        captureRequest = cameraCaptureRequest.build();
                                        session.setRepeatingRequest(captureRequest, new CameraCaptureSession.CaptureCallback() {
                                            @Override
                                            public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
                                                super.onCaptureStarted(session, request, timestamp, frameNumber);
                                            }
                                        },null);
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                                }
                            }, null);

                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {}
                @Override
                public void onError(@NonNull CameraDevice camera, int error) {}
            },null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}
