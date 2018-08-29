package com.aserbao.aserbaosandroid.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.media.audio.AudioRecordActivity;
import com.aserbao.aserbaosandroid.media.exoplayer.ExoPlayerPActivity;
import com.aserbao.aserbaosandroid.media.mediacodec.MediaCodecActivity;
import com.aserbao.aserbaosandroid.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.media.videoView.VideoViewActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class MediaActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("video_view",VideoViewActivity.class));
        mClassBeen.add(new ClassBean("audio_record",AudioRecordActivity.class));
        mClassBeen.add(new ClassBean("media_play",MediaPlayerActivity.class));
        mClassBeen.add(new ClassBean("media_codec",MediaCodecActivity.class));
        mClassBeen.add(new ClassBean("exoplayer",ExoPlayerPActivity.class));
    }

}
