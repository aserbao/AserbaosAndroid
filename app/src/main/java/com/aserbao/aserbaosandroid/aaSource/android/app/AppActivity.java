package com.aserbao.aserbaosandroid.aaSource.android.app;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.ActivityClass;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class AppActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Activity", ActivityClass.class));
    }
}
