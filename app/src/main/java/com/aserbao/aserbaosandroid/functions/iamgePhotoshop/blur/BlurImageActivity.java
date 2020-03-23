package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur;

import android.os.Bundle;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.mmin18.MMin18Activity;

public class BlurImageActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("EasyBlur高斯模糊",ImageBlurActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("动态高斯模糊", MMin18Activity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
