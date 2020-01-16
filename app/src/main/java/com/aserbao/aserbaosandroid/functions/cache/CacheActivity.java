package com.aserbao.aserbaosandroid.functions.cache;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.cache.aSimpleCache.ASimpleCacheActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CacheActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {

        mBaseRecyclerBean.add(new BaseRecyclerBean("ASimpleCache", ASimpleCacheActivity.class));
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
