package com.aserbao.aserbaosandroid.aaThird.dagger2.di.data;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;

import dagger.Component;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/6/26 1:26 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.data
 */
@Component(modules = {DatabaseModule.class,ApplicationModule.class})
public interface ApplicationComponent {
    Database database();
    void inject(DaggerActivity daggerActivity);
}
