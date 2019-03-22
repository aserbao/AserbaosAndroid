package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.MyAccessibilityService;
import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.MyAccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class AccessibilityServiceActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("AccessibilityService", MyAccessibilityServiceActivity.class));
    }
}
