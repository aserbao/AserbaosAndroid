package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.DepenModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.SubModule;

import dagger.Component;
import dagger.Subcomponent;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/6/13 11:08 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.components
 */
@Subcomponent(modules = SubModule.class)
public interface SubComponent {

    Wheel provideWheel();

    @Subcomponent.Builder
    interface Builder{
        Builder requestSubModule(SubModule subModule);
        SubComponent build();
    }

}
