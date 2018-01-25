package com.aserbao.aserbaosandroid.opengl;

import android.app.Activity;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class OpenGlBean {
    private String name;
    private Class<?> clazz;

    public OpenGlBean(String name, Class<?> clazz) {
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
}
