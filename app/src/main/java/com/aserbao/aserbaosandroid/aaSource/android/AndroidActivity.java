package com.aserbao.aserbaosandroid.aaSource.android;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.AccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.AppActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.HardwareActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.MaterialActivity;
import com.aserbao.aserbaosandroid.aaSource.android.support.SupportActivity;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.AndroidWidgetClasses;
import com.aserbao.aserbaosandroid.aaSource.java.JavaAct;
import com.example.base.arouter.RouterConfig;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

@Route(path = RouterConfig.JUMP_TO_ANDROID_ACTIVITY)
public class AndroidActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Java基础类", JavaAct.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("App", AppActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Hardware", HardwareActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Material", MaterialActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Support", SupportActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("AccessibilityService", AccessibilityServiceActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("widget.Classes", AndroidWidgetClasses.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
