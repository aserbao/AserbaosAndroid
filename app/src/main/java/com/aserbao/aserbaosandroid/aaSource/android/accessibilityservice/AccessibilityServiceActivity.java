package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.MyAccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class AccessibilityServiceActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("AccessibilityService", MyAccessibilityServiceActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
