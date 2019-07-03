package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Rim;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Tire;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.SubComponent;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 8:29 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules
 */
@Module(subcomponents = SubComponent.class)
public class CarModule {

    /*@Provides
    public Car provide_Car(Wheel wheel, Engine engine){
        Log.e(TAG, " CarModule中的 provide_Car方法被调用了 " );
        return new Car(wheel,engine);
    }*/

   /*
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
        return new Engine(block,cylinder,sparkPlug);
    }
*/
     /*@Provides
    public Block provide_block(){
        return new Block();
    }

    @Provides
    public Cylinder provide_cyclinder(){
        return new Cylinder();
    }

    @Provides
    public SparkPlug provide_sparkPlug(){
        return new SparkPlug();
    }*/
}
