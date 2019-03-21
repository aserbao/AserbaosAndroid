package com.aserbao.aserbaosandroid.aaThird.dagger2.simple;

import javax.inject.Inject;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/19 11:08 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2
 */
public class Student {


    String name ;
    int age;

//    @Inject
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String haveDinner(){
        return "吃晚餐";
    }

    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
