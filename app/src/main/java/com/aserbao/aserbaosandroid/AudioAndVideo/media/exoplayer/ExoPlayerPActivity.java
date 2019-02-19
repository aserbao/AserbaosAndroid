package com.aserbao.aserbaosandroid.AudioAndVideo.media.exoplayer;

import com.aserbao.aserbaosandroid.AudioAndVideo.media.exoplayer.playerlist.PlayListActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class ExoPlayerPActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("无缝播放", PlayListActivity.class));
    }
}
