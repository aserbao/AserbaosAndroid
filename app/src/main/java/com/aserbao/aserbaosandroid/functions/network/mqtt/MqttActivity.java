package com.aserbao.aserbaosandroid.functions.network.mqtt;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.simple.SimpleMqttActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
/**
 * 功能:
 * author aserbao
 * date : On 2018/11/27
 * email: this is empty email
 */
public class MqttActivity extends BaseActivity {
    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Mqtt的简单使用", SimpleMqttActivity.class));
    }
}
