package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.CarModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.SubWheelModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 8:34 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.components
 */
@Singleton
@Component(modules = CarModule.class)
public interface CarComponent {
     Car provide_car();
     SubWheelComponent.Builder requestSubWheelComponent();
     SubEngineComponent.Builder requestSubEngineComponent();
//     void inject(DaggerActivity daggerActivity);
//    SubWheelComponent addCarModule(SubWheelModule subModule);
//    SubWheelComponent getSubComponent(SubWheelModule subModule);

//        Wheel provider_wheel();
//        Engine provider_enginer();
}
