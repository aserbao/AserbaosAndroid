package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

/**
 * 功能: 车
 *
 * @author aserbao
 * @date : On 2019/5/23 11:40 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Car{
    @Inject
    Wheel wheel;
    @Inject
    Engine engine;
    public static final String DAGGER_2_MAKE_A_CAR = "dagger2 make a car";

    @Inject
    public Car(Wheel wheel, Engine engine) {
        this.wheel = wheel;
        this.engine = engine;
    }

    public void drive(){
        Log.e(DAGGER_2_MAKE_A_CAR, "drive: " );
    }
}
