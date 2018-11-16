package com.aserbao.aserbaosandroid.functions.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.network.okhttp.OkhttpActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class NetWorkActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Htpp网络请求", OkhttpActivity.class));
    }
}
