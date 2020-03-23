package com.aserbao.aserbaosandroid.functions.cache;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.cache.aSimpleCache.ASimpleCacheActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class CacheActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {

        mBaseRecyclerBean.add(new BaseRecyclerBean("ASimpleCache", ASimpleCacheActivity.class));
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
