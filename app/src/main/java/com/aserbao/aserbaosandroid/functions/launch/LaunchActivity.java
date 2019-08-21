package com.aserbao.aserbaosandroid.functions.launch;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.launch.demo.LaunchDemoActivity;

public class LaunchActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Launch Demo", LaunchDemoActivity.class));
    }


}
