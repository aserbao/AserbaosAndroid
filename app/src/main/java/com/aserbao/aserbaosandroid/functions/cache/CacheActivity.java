package com.aserbao.aserbaosandroid.functions.cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.cache.aSimpleCache.ASimpleCacheActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class CacheActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("ASimpleCache", ASimpleCacheActivity.class));
    }
}
