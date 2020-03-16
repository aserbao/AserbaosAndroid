package com.aserbao.aserbaosandroid.aaSource.android.support;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.support.constraint.ConstraintLayoutActvity;
import com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CoordinatorLayout.CoordinatorLayoutActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class SupportActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("ConstraintLayout", ConstraintLayoutActvity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("CollapsingToolbarLayout", CoordinatorLayoutActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
