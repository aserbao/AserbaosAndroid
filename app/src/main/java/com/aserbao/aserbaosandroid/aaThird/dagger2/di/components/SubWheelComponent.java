package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.SubEngineModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.SubWheelModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.PerActivity;

import dagger.Subcomponent;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 8:34 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.components
 */
@Subcomponent(modules = {SubWheelModule.class, SubEngineModule.class})
public  interface SubWheelComponent {
    Wheel requestWheel();
    Engine requestEngine();

    @Subcomponent.Builder
    interface Builder{
        Builder requestSubWheelModule(SubWheelModule subModule);
        Builder requestSubEngineModule(SubEngineModule subModule);
        SubWheelComponent build();
    }
}
