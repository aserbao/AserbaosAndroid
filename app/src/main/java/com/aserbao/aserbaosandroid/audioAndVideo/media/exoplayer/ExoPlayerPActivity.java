package com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer.playerlist.PlayListActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ExoPlayerPActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("无缝播放", PlayListActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
