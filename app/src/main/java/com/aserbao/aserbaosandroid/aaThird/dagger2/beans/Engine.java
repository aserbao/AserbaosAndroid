package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 4:51 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Engine {
    Block block;
    Cylinder cylinder;
    SparkPlug sparkPlugs;

    @Inject
    public Engine(Block block, Cylinder cylinder, SparkPlug sparkPlugs) {
        this.block = block;
        this.cylinder = cylinder;
        this.sparkPlugs = sparkPlugs;
        Log.e(TAG, "Engine: 的构造方法" );
    }

    public void make(){
        Log.e(TAG, "make: 引擎制作" );
    }

}
