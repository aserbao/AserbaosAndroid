package com.aserbao.aserbaosandroid.functions.network;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.network.mqtt.MqttActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class NetWorkActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("MQTT协议", MqttActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
