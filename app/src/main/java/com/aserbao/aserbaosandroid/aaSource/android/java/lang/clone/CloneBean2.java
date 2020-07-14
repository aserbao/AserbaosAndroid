package com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone;

import java.io.Serializable;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/7
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone
 */
class CloneBean2 implements Cloneable {

    String name;
    int age;

    public CloneBean2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "CloneBean2{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    public CloneBean2 clone(CloneBean2 aserbao) {
        return new CloneBean2(name, age);
    }
}
