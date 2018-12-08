package com.aserbao.aserbaosandroid.functions.network.mqtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.simple.SimpleMqttActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class MqttActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Mqtt的简单使用", SimpleMqttActivity.class));
    }
}
