package com.aserbao.aserbaosandroid.aaThird.dagger2.di.components;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.modules.DepenModule;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.CustomScope;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes.PerActivity;

import dagger.Component;

@Component(modules = DepenModule.class)
public interface DepenComponent {
//    Car getDependencyCar();
}