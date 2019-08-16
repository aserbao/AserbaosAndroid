package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.ScaleSmoothSlideOneActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method.ScaleSmoothSlideTwoActivity;

public class ScaleSmoothSlideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("第一种实现方式", ScaleSmoothSlideOneActivity.class));
        mClassBeen.add(new ClassBean("第二种实现方式", ScaleSmoothSlideTwoActivity.class));
    }
}
