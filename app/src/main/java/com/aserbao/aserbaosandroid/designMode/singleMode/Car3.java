package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.util.Log;

/**
 * 功能: 饿汉式单例模式
 * @author aserbao
 * @date : On 2019-08-09 11:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.designMode.singleMode
 */
public class Car3 {

    private static Car3 instance = new Car3();

    public static synchronized Car3 getInstance() {
        return instance;
    }

    private static final String TAG = "Car3";
    public void drive(){
        Log.e(TAG, "drive: 滴滴滴，饿汉式单例模式 单例模式开车了" );
    }
}
