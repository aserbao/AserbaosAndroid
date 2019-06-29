package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Rim;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Tire;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;

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
public class SubEngineModule {
    private static final String TAG = "SubEngineModule";

    @Provides
    public Block provide_block(){
        Log.e(TAG, "provide_block: "  );
        return new Block();
    }

    @Provides
    public Cylinder provide_cyclinder(){
        Log.e(TAG, "provide_cyclinder: " );
        return new Cylinder();
    }

    @Provides
    public SparkPlug provide_sparkPlug(){
        Log.e(TAG, "provide_sparkPlug: " );
        return new SparkPlug();
    }
}
