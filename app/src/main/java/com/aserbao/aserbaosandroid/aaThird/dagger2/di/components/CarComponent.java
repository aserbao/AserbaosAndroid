package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.CarModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.TestModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Reusable;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/29 8:34 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.components
 */
@Component()
public  interface CarComponent {
     void inject(DaggerActivity daggerActivity);
}
