package com.aserbao.aserbaosandroid.audioAndVideo.media;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.JCVideoPlayerActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.audio.AudioRecordActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer.ExoPlayerPActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediacodec.MediaCodecActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.videoView.VideoViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class MediaActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("video_view",VideoViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("audio_record",AudioRecordActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("media_play",MediaPlayerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("media_codec",MediaCodecActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("exoplayer",ExoPlayerPActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("JZVideoPlayer", JCVideoPlayerActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
