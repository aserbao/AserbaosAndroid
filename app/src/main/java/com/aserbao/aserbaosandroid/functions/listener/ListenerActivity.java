package com.aserbao.aserbaosandroid.functions.listener;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.listener.constractListener.ConstractListener;

public class ListenerActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("监听联系人变动", ConstractListener.class));
    }

}
