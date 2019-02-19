package com.aserbao.aserbaosandroid.functions.network;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.MqttActivity;
import com.aserbao.aserbaosandroid.functions.network.okhttp.OkhttpActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class NetWorkActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Htpp网络请求", OkhttpActivity.class));
        mClassBeen.add(new ClassBean("MQTT协议", MqttActivity.class));
    }
}
