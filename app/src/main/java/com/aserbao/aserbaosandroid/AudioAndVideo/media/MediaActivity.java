package com.aserbao.aserbaosandroid.AudioAndVideo.media;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.audio.AudioRecordActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.exoplayer.ExoPlayerPActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.mediacodec.MediaCodecActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.videoView.VideoViewActivity;
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
