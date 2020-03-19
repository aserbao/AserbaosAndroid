package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera;

import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.client.RecorderClient;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.listener.IVideoChange;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.softaudiofilter.SetVolumeAudioFilter;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.MediaConfig;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.RecordConfig;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.Size;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.ui.AspectTextureView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordCameraActivity extends AppCompatActivity implements
        TextureView.SurfaceTextureListener, IVideoChange {
    protected String mSaveVideoPath = null;
    @BindView(R.id.preview_textureview)
    AspectTextureView mTextureView;
    @BindView(R.id.btn_start_record)
    Button mBtnStartRecord;
    private int isRecording = 0;
    private ArrayList<String> mp4List;
    private ArrayList<Long> durationList;
    private RecorderClient mRecorderClient;
    private RecordConfig recordConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//状态栏半透明
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record_camera);
        ButterKnife.bind(this);
        initData();
        initStreamingClient();
    }

    private void initData() {
        mSaveVideoPath = Environment.getExternalStorageDirectory().getPath() + "/ych/" + System.currentTimeMillis() + ".mp4";
        mTextureView.setKeepScreenOn(true);
        mTextureView.setSurfaceTextureListener(this);
        mp4List = new ArrayList<>();
        durationList = new ArrayList<>();
    }

    private void initStreamingClient() {
        mRecorderClient = new RecorderClient();
        recordConfig = RecordConfig.obtain();
        recordConfig.setTargetVideoSize(new Size(640, 360));
//        recordConfig.setTargetVideoSize(new Size(1280, 720));
        recordConfig.setSquare(true);
//        recordConfig.setBitRate(750 * 1024);
        recordConfig.setBitRate(720 * 1280);
        recordConfig.setVideoFPS(20);
        recordConfig.setVideoGOP(1);
        recordConfig.setRenderingMode(MediaConfig.Rending_Model_OpenGLES);
        recordConfig.setDefaultCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        int frontDirection, backDirection;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_FRONT, cameraInfo);
        frontDirection = cameraInfo.orientation;
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
        backDirection = cameraInfo.orientation;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recordConfig.setFrontCameraDirectionMode((frontDirection == 90 ? MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_270 : MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_90) | MediaConfig.DirectionMode.FLAG_DIRECTION_FLIP_HORIZONTAL);
            recordConfig.setBackCameraDirectionMode((backDirection == 90 ? MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_90 : MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_270));
        } else {
            recordConfig.setBackCameraDirectionMode((backDirection == 90 ? MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0 : MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_180));
            recordConfig.setFrontCameraDirectionMode((frontDirection == 90 ? MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_180 : MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0) | MediaConfig.DirectionMode.FLAG_DIRECTION_FLIP_HORIZONTAL);
        }
        //save video
        recordConfig.setSaveVideoPath(mSaveVideoPath);
        if (!mRecorderClient.prepare(this, recordConfig)) {
            mRecorderClient = null;
            Toast.makeText(this, "StreamingClient prepare failed", Toast.LENGTH_LONG).show();
            return;
        }
        Size s = mRecorderClient.getVideoSize();
        mTextureView.setAspectRatio(AspectTextureView.MODE_INSIDE, ((double) s.getWidth()) / s.getHeight());

        mRecorderClient.setVideoChangeListener(this);

        mRecorderClient.setSoftAudioFilter(new SetVolumeAudioFilter());
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mRecorderClient != null) {
            mRecorderClient.startPreview(surface, width, height);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        if (mRecorderClient != null) {
            mRecorderClient.updatePreview(width, height);
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mRecorderClient != null) {
            mRecorderClient.stopPreview(true);
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @OnClick({R.id.btn_start_record, R.id.btn_delete_video, R.id.btn_swap_camera, R.id.btn_flash_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_flash_light:
                mRecorderClient.toggleFlashLight();
                break;
            case R.id.btn_swap_camera:
                mRecorderClient.swapCamera();
                break;
            case R.id.btn_start_record:
                if (isRecording == 0) {
                    mBtnStartRecord.setText("stop");
                    mRecorderClient.startRecording();
                } else {
                    mBtnStartRecord.setText("start");
                    mRecorderClient.stopRecording();
                }
                isRecording ^= 1;
                break;
            case R.id.btn_delete_video:
                break;
        }
    }


    @Override
    public void onVideoSizeChanged(int width, int height) {
        mTextureView.setAspectRatio(AspectTextureView.MODE_INSIDE, ((double) width) / height);
    }
}
