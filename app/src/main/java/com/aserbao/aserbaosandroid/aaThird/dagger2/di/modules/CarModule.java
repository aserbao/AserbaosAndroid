package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.SubEngineComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.SubWheelComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.PerActivity;

import javax.inject.Named;
import javax.inject.Provider;
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
public class CarModule {
    private static final String TAG = "CarModule";

    /*@Singleton
    @Provides
    Car provide_car(SubWheelComponent.Builder subWheelBuilder){
        SubWheelComponent build = subWheelBuilder
            .requestSubWheelModule(new SubWheelModule())
            .requestSubEngineModule(new SubEngineModule())
            .build();
        Log.e(TAG, "provide_car: " );
        return new Car(build.requestWheel(),build.requestEngine());
    }*/

    @Named("法拉利")
    @Provides
    Car provide_CarA(Wheel wheel, Engine engine){
        Log.e(TAG, " ok， 你造了一辆法拉利" );
        return new Car(wheel,engine);
    }

    @Named("兰博基尼")
    @Provides
    Car provide_Car(Wheel wheel, Engine engine){
        Log.e(TAG, " ok， 你造了一辆兰博基尼" );
        return new Car(wheel,engine);
    }

    /*@Provides
    public Wheel provide_wheel(Rim rim, Tire tire){
        Log.e(TAG, "provide_wheel: " );
        return new Wheel(rim,tire);
    }
    @Provides
    public Rim provide_rim(){
        return new Rim();
    }
    @Provides
    public Tire provide_tire(){
        return new Tire();
    }*/
   /* @Provides
    public Engine provide_engine(Block block, Cylinder cylinder, SparkPlug sparkPlug){
        Log.e(TAG, "provide_engine: " );
        return new Engine(block,cylinder,sparkPlug);
    }*/

   /* @Provides
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
    }*/
}
