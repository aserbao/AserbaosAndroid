package com.aserbao.aserbaosandroid.functions.network.mqtt.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

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
    }

    @OnClick({R.id.btn_start_link, R.id.btn_send_message,R.id.btn_unlink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_link:
                MQTTManager instance = MQTTManager.getInstance(mContext);
                instance.setMessageHandlerCallBack(new MessageHandlerCallBack() {
                    @Override
                    public void messageSuccess(String topicName, String s) {
                        Log.e(TAG, "messageSuccess: " + s + " topicName = "+ topicName );
                    }
                });
                instance.connect();
                break;
            case R.id.btn_unlink:
                MQTTManager.getInstance(mContext).disconnect();
                break;
            case R.id.btn_send_message:
                for (int i = 0; i < 1; i++) {
                    MQTTManager instance1 = MQTTManager.getInstance(mContext);
                    sendMessage(instance1);
                }
                break;
        }
    }

    public void sendMessage(MQTTManager instance1){
        String text = " 你要对我做什么？";
        String userName = "quigon568";
        int userId = 45554;
        long cuurTime = System.currentTimeMillis()/1000;
        String messageId = generateMessageId(false);
        SendParam sendParam = new SendParam();
        sendParam.setType(10);
            SendParam.MessageBean messageBean = new SendParam.MessageBean();
            messageBean.setMessageType(0);
            messageBean.setMessageId(messageId);
            messageBean.setMessage(text);
            messageBean.setFromPersonToken("5c63f0dfdb599c99a3ed7dfa04434003254599fd");
            messageBean.setChatId("7302_45554");
            messageBean.setT(messageId);
                SendParam.MessageBean.ToPersonBean toPersonBean = new SendParam.MessageBean.ToPersonBean();
                toPersonBean.setId(userId);
                toPersonBean.setNickname(userName);
            messageBean.setToPerson(toPersonBean);
            messageBean.setVideo("");
            messageBean.setPhoto("");
            messageBean.setTime(String.valueOf(cuurTime));
                SendParam.MessageBean.LyricBean lyricBean = new SendParam.MessageBean.LyricBean();
                lyricBean.setId("1");
            messageBean.setLyric(lyricBean);
        sendParam.setMessage(messageBean);
        Gson gson = new Gson();
        String json = gson.toJson(sendParam);
        Log.e(TAG, "sendMessages: " + json );

        String topic = "chat";
        instance1.publish(topic,json,true,1);
    }

    private static String generateMessageId(boolean bigger) {
        StringBuilder result = new StringBuilder();
        String time = String.valueOf(System.currentTimeMillis());
        StringBuilder userBuild = new StringBuilder();
        String userId = String.valueOf("45554");
        userBuild.append(userId);

        String ramdom = null;
        int rInt = new Random().nextInt(1000);
        // 确保生成的随机数有三位
        if (rInt < 100) {
            rInt += 100;
        }

        if(userId.length() < 10){
            for(int i = 0; i < (10 - userId.length()); i++){
                userBuild.append("0");
            }
        }

        // MessageId 是 String，String 使用的是字典排序（按ASCII码比较大小）,所以这里 big 类型的 MessageID
        // 首个字符串为字母，即可保证比普通的 messageID 大
        if (bigger) {
            result.append("fake_");
        }
        result.append(time);
        result.append(String.valueOf(rInt));
        result.append(userBuild.toString());
        result.append("009");

        return result.toString();
    }
}
