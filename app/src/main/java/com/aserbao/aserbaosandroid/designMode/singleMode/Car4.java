package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.util.Log;

/**
 * 功能: 双检锁/双重校验锁单例模式
 * @author aserbao
 * @date : On 2019-08-09 11:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.designMode.singleMode
 */
public class Car4 {

    private static Car4 instance ;

    public static Car4 getSingleton() {
        if (instance == null) {
            synchronized (Car4.class) {
                if (instance == null) {
                    instance = new Car4();
                }
            }
        }
        return instance;
    }
    private static final String TAG = "Car4";
    public void drive(){
        Log.e(TAG, "drive: 滴滴滴，双检锁/双重校验锁单例模式 开车了" );
    }
}
