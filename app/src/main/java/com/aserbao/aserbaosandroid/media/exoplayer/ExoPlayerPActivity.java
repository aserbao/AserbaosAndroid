package com.aserbao.aserbaosandroid.media.exoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.media.exoplayer.playerlist.PlayListActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class ExoPlayerPActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("无缝播放", PlayListActivity.class));
    }
}
