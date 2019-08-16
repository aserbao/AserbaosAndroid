package com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer;

import com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer.playerlist.PlayListActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class ExoPlayerPActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("无缝播放", PlayListActivity.class));
    }
}
