package com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.live.JCLivePlayerActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class JCVideoPlayerActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("直播", JCLivePlayerActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
