package com.aserbao.aserbaosandroid.functions.cache;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.cache.aSimpleCache.ASimpleCacheActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class CacheActivity extends BaseActivity {


    @Override
    public void initGetData() {

        mClassBeen.add(new ClassBean("ASimpleCache", ASimpleCacheActivity.class));
    }
}
