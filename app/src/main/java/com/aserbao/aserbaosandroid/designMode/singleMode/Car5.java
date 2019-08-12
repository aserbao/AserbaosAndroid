package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.util.Log;

/**
 * 功能: 静态内部类单例模式
 * @author aserbao
 * @date : On 2019-08-09 11:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.designMode.singleMode
 */
public class Car5 {

    private static class SingletonHolder {
        private static final Car5 instance = new Car5();
    }
    public static final Car5 getInstance() {
        return SingletonHolder.instance;
    }

    private static final String TAG = "Car5";
    public void drive(){
        Log.e(TAG, "drive: 滴滴滴，静态内部类单例模式 开车了" );
    }
}
