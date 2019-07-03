package com.aserbao.aserbaosandroid.aaThird.dagger2.di.data;

import dagger.Module;
import dagger.Provides;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/6/26 1:31 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.data
 */
@Module
public class ApplicationModule {
    @Provides
    Integer providesNumberOfCores() {
        return 2;
    }
}
