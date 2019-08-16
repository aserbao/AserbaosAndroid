package com.aserbao.aserbaosandroid.functions.network;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.MqttActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class NetWorkActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("MQTT协议", MqttActivity.class));
    }
}
