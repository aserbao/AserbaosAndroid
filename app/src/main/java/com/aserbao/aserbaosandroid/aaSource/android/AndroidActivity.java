package com.aserbao.aserbaosandroid.aaSource.android;

import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.AccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.AppActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.MaterialActivity;
import com.aserbao.aserbaosandroid.aaSource.android.support.SupportActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class AndroidActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("App", AppActivity.class));
        mClassBeen.add(new ClassBean("Material", MaterialActivity.class));
        mClassBeen.add(new ClassBean("Support", SupportActivity.class));
        mClassBeen.add(new ClassBean("AccessibilityService", AccessibilityServiceActivity.class));
    }
}
