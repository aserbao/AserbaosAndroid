package com.aserbao.aserbaosandroid.aaSource.android.media.mediaRecorder;

import android.Manifest;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.io.IOException;

public class MediaRecorderActivity extends BaseRecyclerViewActivity {


    private MediaRecorder mMediaRecorder;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("MediaRecorder录制音频",0));
        // 请求权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new  String[]{Manifest.permission.CHANGE_CONFIGURATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},0);
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                if (isLongClick) {
                    stop();
                }else{
                    useMediaRecorderAudio();
                }
                break;
        }
    }

    private static final String MUSIC_PATH_NAME = Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.mp3";
    /**
     * 使用MediaRecorder录制音频
     */
    private void useMediaRecorderAudio() {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mMediaRecorder.setOutputFile(MUSIC_PATH_NAME);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaRecorder.start();   // Recording is now started
    }


    public void stop(){
        if (mMediaRecorder != null) {
            mMediaRecorder.stop();
            mMediaRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
            mMediaRecorder.release();
        }
    }
}
