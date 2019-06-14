package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能: 轮毂
 *
 * @author aserbao
 * @date : On 2019/5/29 5:14 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Rim {
    @Inject
    public Rim() {
        Log.e(TAG, "Rim的构造方法" );
    }
}
