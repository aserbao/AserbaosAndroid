package com.aserbao.aserbaosandroid.aaSource.android;

import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.AccessibilityServiceActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.AppActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.HardwareActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.MaterialActivity;
import com.aserbao.aserbaosandroid.aaSource.android.support.SupportActivity;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.AndroidWidgetClasses;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AndroidActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("App", AppActivity.class));
        mClassBeen.add(new ClassBean("Hardware", HardwareActivity.class));
        mClassBeen.add(new ClassBean("Material", MaterialActivity.class));
        mClassBeen.add(new ClassBean("Support", SupportActivity.class));
        mClassBeen.add(new ClassBean("AccessibilityService", AccessibilityServiceActivity.class));
        mClassBeen.add(new ClassBean("widget.Classes", AndroidWidgetClasses.class));
    }
}
