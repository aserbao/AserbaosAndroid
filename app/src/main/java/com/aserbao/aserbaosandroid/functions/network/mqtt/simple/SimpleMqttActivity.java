package com.aserbao.aserbaosandroid.functions.network.mqtt.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleMqttActivity extends AppCompatActivity {
    private static final String TAG = "SimpleMqttActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mqtt);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.btn_configuration, R.id.send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_configuration:
                MQTTManager instance = MQTTManager.getInstance(mContext);
                instance.setMessageHandlerCallBack(new MessageHandlerCallBack() {
                    @Override
                    public void messageSuccess(String topicName, String s) {
                        Log.e(TAG, "messageSuccess: " + s);
                    }
                });
                instance.connect();
                break;
            case R.id.send_btn:
                break;
        }
    }


    public void configuration(){

    }


}
