package com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules;

import com.aserbao.aserbaosandroid.aaThird.dagger2.simple.Student;

import dagger.Module;
import dagger.Provides;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/20 2:26 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules
 */
@Module
public class ActivityModule {
    @Provides
    public Student providesStudent(){
        return new Student("123",18);
    }
}
