package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.os.Bundle;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.commonData.StaticFinalValues;

public class BlendModeActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"CLEAR",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_OVER",3));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_OVER",4));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_IN",5));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_IN",6));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_OUT",7));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_OUT",8));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_ATOP",9));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_ATOP",10));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"XOR",11));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DARKEN",16));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"LIGHTEN",17));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"MULTIPLY",13));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SCREEN",14));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"ADD",12));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"OVERLAY",15));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
