package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.SubEngineComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.SubWheelComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.PerActivity;

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
@Module(/*subcomponents = {SubWheelComponent.class, SubEngineComponent.class}*/)
public class CarModule {
    private static final String TAG = "CarModule";


    /*
    @Singleton
    @Provides
    Wheel provide_wheel(Provider< SubWheelComponent.Builder> subWheelBuilder){
        return subWheelBuilder.get()
            .requestModule(new SubWheelModule())
            .build()
            .requestWheel();
    }

    @Singleton
    @Provides
    Engine provide_engine(Provider< SubEngineComponent.Builder> subEngineBuilder){
        return subEngineBuilder.get()
            .requestModule(new SubEngineModule())
            .build()
            .provideEngine();
    }*/


    /*@Provides
    public Car provide_Car(Wheel wheel, Engine engine){
        Log.e(TAG, " carModule中 provide_Car: " );
        return new Car(wheel,engine);
    }
    @Provides
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
