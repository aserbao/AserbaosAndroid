package com.aserbao.aserbaosandroid.functions.network.mqtt;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.simple.SimpleMqttActivity;
import com.example.base.base.beans.BaseRecyclerBean;
/**
 * 功能:
 * author aserbao
 * date : On 2018/11/27
 * email: this is empty email
 */
public class MqttActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Mqtt的简单使用", SimpleMqttActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
