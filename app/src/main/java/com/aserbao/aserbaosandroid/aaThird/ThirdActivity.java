package com.aserbao.aserbaosandroid.aaThird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.videocache.VideoCacheActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class ThirdActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Dagger2", DaggerActivity.class));
        mClassBeen.add(new ClassBean("VideoCache", VideoCacheActivity.class));
    }
}
