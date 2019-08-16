package com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer;

import com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.live.JCLivePlayerActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class JCVideoPlayerActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("直播", JCLivePlayerActivity.class));
    }
}
