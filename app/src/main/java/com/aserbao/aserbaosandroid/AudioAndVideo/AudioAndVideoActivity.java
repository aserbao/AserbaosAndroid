package com.aserbao.aserbaosandroid.AudioAndVideo;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.AudioAndVideo.detail_Info.DetailInfoActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.jnis.JnisActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.media.MediaActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class AudioAndVideoActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("多媒体详情", DetailInfoActivity.class));
        mClassBeen.add(new ClassBean("播放器", MediaActivity.class));
        mClassBeen.add(new ClassBean("JNI", JnisActivity.class));
    }
}
