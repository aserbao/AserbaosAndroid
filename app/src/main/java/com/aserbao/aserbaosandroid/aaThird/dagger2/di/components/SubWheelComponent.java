package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
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
@Subcomponent(modules = SubWheelModule.class)
public  interface SubWheelComponent {
    @PerActivity
    Wheel requestWheel();

    @Subcomponent.Builder
    interface Builder{
        Builder requestModule(SubWheelModule subModule);
        SubWheelComponent build();
    }
}
