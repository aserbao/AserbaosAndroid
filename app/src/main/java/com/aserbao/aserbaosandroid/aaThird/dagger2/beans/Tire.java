package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能:轮胎
 *
 * @author aserbao
 * @date : On 2019/5/29 5:12 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Tire {
    @Inject
    public Tire() {
        Log.e(TAG, "Tire: 的构造方法" );
    }

}
