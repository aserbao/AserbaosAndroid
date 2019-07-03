package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Rim;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Tire;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;

import dagger.Module;
import dagger.Provides;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/6/17 10:40 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules
 */
@Module
public class SubModule {
    @Provides
    public Wheel provide_wheel(Rim rim, Tire tire){
        return new Wheel(rim,tire);
    }
}
