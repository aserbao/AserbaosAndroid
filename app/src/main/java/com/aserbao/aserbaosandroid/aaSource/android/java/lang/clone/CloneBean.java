package com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone;

import java.io.Serializable;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/7
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone
 */
class CloneBean implements Serializable {

    String name;
    int age;

    public CloneBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "CloneBean{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
