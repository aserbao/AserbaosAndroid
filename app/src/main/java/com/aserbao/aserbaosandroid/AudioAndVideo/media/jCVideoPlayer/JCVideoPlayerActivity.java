package com.aserbao.aserbaosandroid.AudioAndVideo.media.jCVideoPlayer;

import com.aserbao.aserbaosandroid.AudioAndVideo.media.jCVideoPlayer.live.JCLivePlayerActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class JCVideoPlayerActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("直播", JCLivePlayerActivity.class));
    }
}
