package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.util.Log;

/**
 * 功能: 懒汉式线程不安全单例模式
 * @author aserbao
 * @date : On 2019-08-09 11:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.designMode.singleMode
 */
public class Car1 {

    private static Car1 instance;

    public static Car1 getInstance() {
        if (instance == null) {
            instance = new Car1();
        }
        return instance;
    }

    private static final String TAG = "Car1";
    public void drive(){
        Log.e(TAG, "drive: 滴滴滴，懒汉式，线程不安全 单例模式开车了" );
    }
}
