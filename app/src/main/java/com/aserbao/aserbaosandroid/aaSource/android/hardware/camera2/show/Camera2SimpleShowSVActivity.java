package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.Facing;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.cameraUtils.Camera2Utils;
import com.aserbao.aserbaosandroid.cameraTest.AutoFitTextureView;
import com.example.base.utils.log.ALogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: aserbao
 * @date:2020/8/5 10:42 AM
 * @package:com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show
 * @describle: Camera2 简单相机预览
 */
public class Camera2SimpleShowSVActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    public final String TAG = this.getClass().getSimpleName();;
    public static final int MAX_PREVIEW_WIDTH = 1920;
    public static final int MAX_PREVIEW_HEIGHT = 1080;
    public static final int RECOMMEND_WIDTH = 1920;
    public static final int RECOMMEND_HEIGHT = 1080;
    float previewWHRatio = 9f / 16f; // 预览尺寸比例
    /**
     * 最佳视频尺寸
     */
    Size optimalPreviewSize;

    @BindView(R.id.mSurface)
    AutoFitTextureView mAutoTextView;
    public SurfaceHolder mHolder;
    private CaptureRequest captureRequest;

    private Facing cameraFacing = Facing.BACK;

    CameraDevice mCameraDevice;
    CameraCaptureSession mCaptureSession;
    CaptureRequest.Builder previewCaptureBuild;

    private CameraDevice.StateCallback mStateCallBack = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            ALogUtils.d(TAG, "onOpened: camera=" + camera);
            mCameraDevice = camera;
            startPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            ALogUtils.d(TAG, "onDisconnected() called with: camera = [" + camera + "]");
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            ALogUtils.d(TAG, "onError: error=" + error);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2_simple_show);
        ButterKnife.bind(this);
        mAutoTextView.setSurfaceTextureListener(this);
    }

    /**
     * 打开相机
     */
    private void openCamera(Facing facing) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show();
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String mCameraId = "";
        try {
            String[] cameraIdList = manager.getCameraIdList();
            for (String cameraId : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics(cameraId);
                Integer cameraDirection = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing.getValue() == cameraDirection) {
                    setUpCameraCharacteristics(cameraCharacteristics);//获取一些相机参数
                    mCameraId = cameraId;
                    break;
                }
            }
            manager.openCamera(mCameraId, mStateCallBack, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 配置相机功能参数
     * @param cameraCharacteristics
     */
    private void setUpCameraCharacteristics(CameraCharacteristics cameraCharacteristics) {
        Camera2Utils.setupCameraCharacteristics(cameraCharacteristics);
        Camera2Utils.getCameraStreamInfo(cameraCharacteristics);
        /** 拿到当前相机的尺寸预览列表**/
        StreamConfigurationMap map = cameraCharacteristics.get(
            CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        optimalPreviewSize = Camera2Utils.chooseOptimalPreviewSize(
            map.getOutputSizes(SurfaceTexture.class), RECOMMEND_WIDTH, RECOMMEND_HEIGHT,  previewWHRatio);
        Log.e(TAG, "setUpCameraCharacteristics: " + optimalPreviewSize.toString() );
        mAutoTextView.setAspectRatio(previewWHRatio);
    }

    /**
     * 开启预览
     */
    private void startPreview(){
        SurfaceTexture surfaceTexture = mAutoTextView.getSurfaceTexture();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Surface surface = new Surface(surfaceTexture);
            surfaceTexture.setDefaultBufferSize(optimalPreviewSize.getWidth(), optimalPreviewSize.getHeight());
            List<Surface> mSurfaces = new ArrayList<>();
            mSurfaces.add(surface);
            try {
                previewCaptureBuild = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                previewCaptureBuild.addTarget(surface);
                mCameraDevice.createCaptureSession(mSurfaces, new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(@NonNull CameraCaptureSession session) {
                        ALogUtils.d(TAG, "onConfigured: session=" + session );
                        mCaptureSession = session;
                        updatePreview();
                    }
                    @Override
                    public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                       ALogUtils.d(TAG, "onConfigureFailed() called with: session = [" + session + "]");
                    }
                }, null);
            } catch (CameraAccessException e) {
                ALogUtils.d(TAG, "onOpened: 失败="+e.toString() );
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
                    ALogUtils.d(TAG, "onCaptureStarted: session="+session+ " request="+request+ " timestamp="+timestamp+ " frameNumber="+frameNumber );
                }
            },null);
        } catch (Exception e) {
            e.printStackTrace();
            ALogUtils.d(TAG, "onConfigured: 出错 "+ e.toString() );
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        openCamera(cameraFacing);
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

    /**
     * 切换摄像头
     * @param view
     */
    public void switchCamera(View view) {
        if(cameraFacing == Facing.BACK){
            cameraFacing=Facing.FRONT;
        }else{
            cameraFacing=Facing.BACK;
        }
        mCameraDevice.close();
        openCamera(cameraFacing);
    }


}
