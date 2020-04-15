package com.aserbao.aserbaosandroid.aaSource.android.media.mediaRecorder;

import android.media.MediaRecorder;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.io.IOException;

import static com.example.base.utils.data.StaticFinalValues.MUSIC_PATH_NAME;

public class MediaRecorderAudioActivity extends BaseRecyclerViewActivity {

    private MediaRecorder mMediaRecorder;

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("点击开始录音",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("点击暂停录音",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                useMediaRecorderAudio();
                break;
            case 1:
                stop();
                break;
        }
    }

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
