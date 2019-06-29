package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Rim;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Tire;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 8:29 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules
 */
@Module
public class SubWheelModule {
    private static final String TAG = "SubWheelModule";

    @Provides
    public Rim provide_rim(){
        Log.e(TAG, "provide_rim: " );
        return new Rim();
    }
    @Provides
    public Tire provide_tire(){
        Log.e(TAG, "provide_tire: " );
        return new Tire();
    }
}
