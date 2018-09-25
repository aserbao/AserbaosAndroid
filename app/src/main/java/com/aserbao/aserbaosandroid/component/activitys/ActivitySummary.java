package com.aserbao.aserbaosandroid.component.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.component.activitys.lifeCycle.LifeCycleActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class ActivitySummary extends BaseActivity {



    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("生命周期", LifeCycleActivity.class));
    }
}
