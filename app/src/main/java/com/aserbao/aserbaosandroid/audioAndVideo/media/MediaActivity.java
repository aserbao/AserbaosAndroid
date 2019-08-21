package com.aserbao.aserbaosandroid.audioAndVideo.media;

import com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.JCVideoPlayerActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.audio.AudioRecordActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer.ExoPlayerPActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediacodec.MediaCodecActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.videoView.VideoViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class MediaActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("video_view",VideoViewActivity.class));
        mClassBeen.add(new ClassBean("audio_record",AudioRecordActivity.class));
        mClassBeen.add(new ClassBean("media_play",MediaPlayerActivity.class));
        mClassBeen.add(new ClassBean("media_codec",MediaCodecActivity.class));
        mClassBeen.add(new ClassBean("exoplayer",ExoPlayerPActivity.class));
        mClassBeen.add(new ClassBean("JZVideoPlayer", JCVideoPlayerActivity.class));
    }

}
