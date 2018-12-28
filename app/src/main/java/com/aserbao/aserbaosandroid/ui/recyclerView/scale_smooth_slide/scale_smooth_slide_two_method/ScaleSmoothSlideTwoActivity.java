package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.common.ScaleBaseActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method.adapter.SmoothTwoAdapter;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method.library.AnimManager;

public class ScaleSmoothSlideTwoActivity extends ScaleBaseActivity {

    @Override
    public void init() {
        mScaleSmoothSlideGalleryView.setVisibility(View.VISIBLE);
        SmoothTwoAdapter smoothTwoAdapter = new SmoothTwoAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mScaleSmoothSlideGalleryView.setLayoutManager(linearLayoutManager);
        mScaleSmoothSlideGalleryView.setAdapter(smoothTwoAdapter);
        mScaleSmoothSlideGalleryView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(0, 40)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.50f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP);            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
        mScaleSmoothSlideGalleryView.scrollToPosition(500);
        mScaleSmoothSlideGalleryView.smoothScrollToPosition(501);
    }
}
