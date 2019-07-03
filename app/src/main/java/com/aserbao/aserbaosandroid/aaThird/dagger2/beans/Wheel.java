package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能: 车轮
 *
 * @author aserbao
 * @date : On 2019/5/29 5:11 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Wheel {
    Rim rim;
    Tire tire;
    @Inject
    public Wheel(Rim rim, Tire tire) {
        this.rim = rim;
        this.tire = tire;
        Log.e(TAG, "Wheel的构造方法 " );
    }

    public void make(){
        Log.e(TAG, "make: 轮子制作" );
    }
}
