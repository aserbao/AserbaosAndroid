package com.aserbao.aserbaosandroid.system;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.system.handlers.HandlerActivity;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/20
 * email: this is empty email
 */
public class SystemActivity extends BaseActivity {
    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Handler的使用", HandlerActivity.class));
    }
}
