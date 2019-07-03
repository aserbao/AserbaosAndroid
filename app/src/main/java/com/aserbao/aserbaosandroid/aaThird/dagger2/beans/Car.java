package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import com.aserbao.aserbaosandroid.algorithm.list.Student;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 功能: 车
 *
 * @author aserbao
 * @date : On 2019/5/23 11:40 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Car{
    Wheel wheel;
    Engine engine;
    public static final String TAG = "dagger2 make a car";


    public Car(Wheel wheel, Engine engine) {
        this.wheel = wheel;
        this.engine = engine;
        Log.e(TAG, "Car: 的构造方法" );
    }


    @Inject
    public void startCar(){
        Log.e(TAG, "startCar: 汽车启动 " );
    }

    public void drive(){
        Log.e(TAG, "drive: 开车，嘟嘟嘟……" );
    }
}
