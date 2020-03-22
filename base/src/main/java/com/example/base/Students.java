package com.example.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/22
 * @project: AserbaosAndroid
 * @package: com.example.base
 */
@Entity
public class Students {
    private int Student;

    @Generated(hash = 1121733484)
    public Students(int Student) {
        this.Student = Student;
    }

    @Generated(hash = 174834727)
    public Students() {
    }

    public int getStudent() {
        return this.Student;
    }

    public void setStudent(int Student) {
        this.Student = Student;
    }
}
