package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.util.Log;

/**
 * 功能: 懒汉式线程安全单例模式
 * @author aserbao
 * @date : On 2019-08-09 11:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.designMode.singleMode
 */
public class Car2 {

    private static Car2 instance;

    public static synchronized Car2 getInstance() {
        if (instance == null) {
            instance = new Car2();
        }
        return instance;
    }

    private static final String TAG = "Car2";
    public void drive(){
        Log.e(TAG, "drive: 滴滴滴，懒汉式，线程安全 单例模式开车了" );
    }
}
