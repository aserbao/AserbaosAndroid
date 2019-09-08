package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

public class BlendModeActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"CLEAR",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_OVER",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_OVER",4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_IN",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_IN",6));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_OUT",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_OUT",8));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SRC_ATOP",9));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DST_ATOP",10));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"XOR",11));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"DARKEN",16));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"LIGHTEN",17));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"MULTIPLY",13));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"SCREEN",14));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"ADD",12));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_BLEND_MODE,"OVERLAY",15));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {

    }
}
