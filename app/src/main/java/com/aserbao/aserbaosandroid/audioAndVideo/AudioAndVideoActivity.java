package com.aserbao.aserbaosandroid.AudioAndVideo;

import com.aserbao.aserbaosandroid.audioAndVideo.detail_Info.DetailInfoActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.jnis.JnisActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.MediaActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AudioAndVideoActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("多媒体详情", DetailInfoActivity.class));
        mClassBeen.add(new ClassBean("播放器", MediaActivity.class));
        mClassBeen.add(new ClassBean("JNI", JnisActivity.class));
    }
}
