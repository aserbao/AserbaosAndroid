package com.aserbao.aserbaosandroid.comon.base.beans;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class ClassBean {
    @NonNull
    private String name;
    private Class<?> clazz;
    int flag;


    public ClassBean(String name, Class<?> clazz, int flag) {
        this.name = name;
        this.clazz = clazz;
        this.flag = flag;
    }

    public ClassBean(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
