package com.aserbao.aserbaosandroid.aaSource.android.media.mediaRecorder;

import android.graphics.PixelFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.CameraUtils;
import com.aserbao.aserbaosandroid.AUtils.utils.MainLooper;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaRecorderVideoActivity extends BaseRecyclerViewActivity{
    private static final String TAG = "MediaRecorderVideoActivity";
    private int recomVideoWidth = 2160, recomVideoHeight = 1080;
    Camera mCamera;
    @Override
    public void initGetData() {
        mBaseRecyclerViewFl.bringChildToFront(mOpenglRecyclerView);
        mBaseRecyclerBeen.add(new BaseRecyclerBean("开始录制",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("停止录制",1));
        initCamera();
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position) {
            case 0:
                mMediaRecorder = startRecord(StaticFinalValues.STORAGE_TEMP_VIDEO_PATH);
                break;
            case 1:
                stopRecord(mMediaRecorder);
                break;
        }

    }
    SurfaceView mSurfaceView;
    Camera.Size mOptimalVideoSize;
    /**
     * 初始化相机
     */
    private void initCamera() {
        View root = addLayoutToFrameLayout(R.layout.surface_view_layout, true);
        mSurfaceView = ((SurfaceView) root.findViewById(R.id.surface_view));
        SurfaceHolder mSurfaceViewHolder = mSurfaceView.getHolder();
        mSurfaceViewHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated() called with: holder = [" + holder + "]");
                try {
                    // Open the Camera in preview mode
                    mCamera = Camera.open(0);
                    mOrientation = 90;
                    mCamera.setDisplayOrientation(mOrientation);
                    mCamera.setPreviewDisplay(holder);
                    Camera.Parameters mParameters = mCamera.getParameters();
                    mCamera.setParameters(mParameters);
                    getBeatPreview(mParameters);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(TAG, "surfaceChanged() called with: holder = [" + holder + "], format = [" + format + "], width = [" + width + "], height = [" + height + "]");
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            Camera.Parameters mParameters = mCamera.getParameters();
                            mParameters.setPictureFormat(PixelFormat.JPEG); //图片输出格式
//                          mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//预览持续发光
                            mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//持续对焦模式
                            mCamera.setParameters(mParameters);
                            mCamera.startPreview();
                            mCamera.cancelAutoFocus();
                        }
                    }
                });
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed() called with: holder = [" + holder + "]");
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
            }
        });
    }

    MediaRecorder mMediaRecorder;
    private static int mOrientation = 0;
    public MediaRecorder startRecord(String outputPath){
        //创建MediaRecorder
        MediaRecorder mMediaRecorder = new MediaRecorder();
        mMediaRecorder.reset();
        mCamera.unlock();
        //创建录音文件
        File mRecorderFile = new File(outputPath);
        try {
            if (!mRecorderFile.getParentFile().exists()) mRecorderFile.getParentFile().mkdirs();
            mRecorderFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setOrientationHint(mOrientation);
        //从麦克风采集
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        //使用CamcorderProfile做配置的话，输出格式，音频编码，视频编码 不要写,否则会报错（崩溃）
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setVideoEncodingBitRate(recomVideoWidth * recomVideoHeight * 30 * 16);
        mMediaRecorder.setVideoSize(recomVideoWidth, recomVideoHeight);
        mMediaRecorder.setVideoFrameRate(30);
        mMediaRecorder.setOutputFile(mRecorderFile.getAbsolutePath());
        mMediaRecorder.setPreviewDisplay(mSurfaceView.getHolder().getSurface());
        //开始录音
        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMediaRecorder;
    }
    public void stopRecord(MediaRecorder mediaRecorder){
        if (mediaRecorder != null && mCamera != null ){
            mediaRecorder.stop();
            mediaRecorder.release();
            mCamera.lock();
        }
    }


    private void getBeatPreview(Camera.Parameters  parms) {
        for (Camera.Size size : parms.getSupportedPreviewSizes()) {
            Log.e(TAG, "getBeatPreview: size.width = " + size.width + " height ="+ size.height );
        }
        for (Camera.Size supportedVideoSize : parms.getSupportedVideoSizes()) {
            Log.e(TAG, "getBeatPreview: supportedVideoSize.width =  " + supportedVideoSize.width + " supportedVideoSize.height = " + supportedVideoSize.height );
        }
        List<Camera.Size> supportedVideoSizes = parms.getSupportedVideoSizes();
        List<Camera.Size> supportedPreviewSizes = parms.getSupportedPreviewSizes();
        int screenWidth = AserbaoApplication.screenWidth;
        int screenHeight = AserbaoApplication.screenHeight;
        mOptimalVideoSize = CameraUtils.getOptimalVideoSize(supportedVideoSizes, supportedPreviewSizes, screenWidth, screenHeight);
        parms.setPreviewSize(mOptimalVideoSize.width,mOptimalVideoSize.height);
        Log.e(TAG, "getBeatPreview: mOptimalVideoSize.width = " +mOptimalVideoSize.width + " mOptimalVideoSize.height = "
            + mOptimalVideoSize.height + " screenWidth = " +screenWidth+ " screenHeight = " +screenHeight  );
    }
}
