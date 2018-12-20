package com.aserbao.aserbaosandroid.system.handlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.system.handlers.principle_for_handler.PrincipleHanlderActivity;

public class HandlerActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Handler 的原理及概念", PrincipleHanlderActivity.class));
    }
}
