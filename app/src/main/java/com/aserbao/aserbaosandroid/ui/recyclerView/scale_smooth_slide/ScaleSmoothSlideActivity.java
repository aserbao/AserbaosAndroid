package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide;

import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.ScaleSmoothSlideOneActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method.ScaleSmoothSlideTwoActivity;

public class ScaleSmoothSlideActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("第一种实现方式", ScaleSmoothSlideOneActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("第二种实现方式", ScaleSmoothSlideTwoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
