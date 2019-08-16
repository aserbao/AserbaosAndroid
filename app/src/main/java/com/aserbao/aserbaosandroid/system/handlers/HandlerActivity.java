package com.aserbao.aserbaosandroid.system.handlers;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.system.handlers.principle_for_handler.PrincipleHanlderActivity;

public class HandlerActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Handler 的原理及概念", PrincipleHanlderActivity.class));
    }
}
