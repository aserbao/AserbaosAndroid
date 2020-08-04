package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.cameraTest.AutoFitTextureView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera2SimpleShowSVActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final String TAG = "Camera2SurfaceViewActiv";
    @BindView(R.id.mSurface)
    AutoFitTextureView mAutoTextView;
    public SurfaceHolder mHolder;
    private CaptureRequest captureRequest;

    private String mFrontCameraId = "1";
    private String mBackCameraId = "0";
    private String cameraId = mBackCameraId;

    CameraDevice mCameraDevice;
    CameraCaptureSession mCaptureSession;
    CaptureRequest.Builder previewCaptureBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2_simple_show);
        ButterKnife.bind(this);

        mAutoTextView.setSurfaceTextureListener(this);
    }

    /**
     * 打开相机权限
     */
    public void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        //CameraManager cameraManager1 = getSystemService(CameraManager.class);
//        CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics("0");
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e(TAG, "openCamera: cameraId= "+cameraId );
            manager.openCamera(cameraId, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    mCameraDevice = camera;
                    startPreview();
                    Log.e(TAG, "onOpened: camera=" + camera );
                }
                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {
                    Log.d(TAG, "onDisconnected() called with: camera = [" + camera + "]");
                }
                @Override
                public void onError(@NonNull CameraDevice camera, int error) {
                    Log.e(TAG, "onError: error=" + error );
                }
            },null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "openCamera:出错 "+ e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 开启预览
     */
    private void startPreview(){
        SurfaceTexture surfaceTexture = mAutoTextView.getSurfaceTexture();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Surface surface = new Surface(surfaceTexture);
            surfaceTexture.setDefaultBufferSize(1920,1080);
            List<Surface> mSurfaces = new ArrayList<>();
            mSurfaces.add(surface);
            try {
                previewCaptureBuild = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                previewCaptureBuild.addTarget(surface);
                mCameraDevice.createCaptureSession(mSurfaces, new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(@NonNull CameraCaptureSession session) {
                        Log.e(TAG, "onConfigured: session=" + session );
                        mCaptureSession = session;
                        updatePreview();
                    }
                    @Override
                    public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                        Log.d(TAG, "onConfigureFailed() called with: session = [" + session + "]");
                    }
                }, null);
            } catch (CameraAccessException e) {
                Log.e(TAG, "onOpened: 失败="+e.toString() );
                e.printStackTrace();
            }
        }
    }

    private void updatePreview() {
        try {
            captureRequest = previewCaptureBuild.build();
            mCaptureSession.setRepeatingRequest(captureRequest, new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
                    super.onCaptureStarted(session, request, timestamp, frameNumber);
                    Log.e(TAG, "onCaptureStarted: session="+session+ " request="+request+ " timestamp="+timestamp+ " frameNumber="+frameNumber );
                }
            },null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "onConfigured: 出错 "+ e.toString() );
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        openCamera();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {}

    public void switchCamera(View view) {
        if(cameraId.equals(mBackCameraId)){
            cameraId = mFrontCameraId;
        }else{
            cameraId = mBackCameraId;
        }
        mCameraDevice.close();
        openCamera();
    }
}
