package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.common.ScaleBaseActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.adapter.SmoothAdapter;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.library.CardScaleHelper;

public class ScaleSmoothSlideOneActivity extends ScaleBaseActivity {


    @Override
    public void init() {
        SmoothAdapter smoothAdapter = new SmoothAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mScaleSmoothSlideRecyclerView.setVisibility(View.VISIBLE);
        mScaleSmoothSlideRecyclerView.setLayoutManager(linearLayoutManager);
        mScaleSmoothSlideRecyclerView.setAdapter(smoothAdapter);
        CardScaleHelper mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(200);
        mCardScaleHelper.attachToRecyclerView(mScaleSmoothSlideRecyclerView);
    }
}
