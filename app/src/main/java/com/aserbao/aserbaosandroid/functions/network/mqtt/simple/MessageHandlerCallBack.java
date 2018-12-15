package com.aserbao.aserbaosandroid.functions.network.mqtt.simple;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/27
 * email: 1142803753@qq.com
 */
interface MessageHandlerCallBack {
    void messageSuccess(String topicName, String s);
}
