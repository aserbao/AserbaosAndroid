package com.aserbao.aserbaosandroid.aaSource.android.material;

import android.content.Intent;

import com.aserbao.aserbaosandroid.aaSource.android.material.button.MaterialButtonActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.floatView.FloatWindowActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class MaterialActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("MaterialButtonActivity", MaterialButtonActivity.class));
        mClassBeen.add(new ClassBean("悬浮窗", FloatWindowActivity.class,Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
