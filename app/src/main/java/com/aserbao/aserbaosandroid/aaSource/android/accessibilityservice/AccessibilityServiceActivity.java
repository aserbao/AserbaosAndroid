package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice;

import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.MyAccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AccessibilityServiceActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("AccessibilityService", MyAccessibilityServiceActivity.class));
    }
}
