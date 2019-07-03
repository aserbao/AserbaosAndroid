package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import android.util.Log;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.CustomScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;


/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/30 8:32 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules
 */
@Module
public class DepenModule {

    /*@Provides
    public Car provide_Car(Wheel wheel, Engine engine){
        Log.e(TAG, " DepenModule 中的 provide_Car方法被调用了 " );
        return new Car(wheel,engine);
    }*/
}
