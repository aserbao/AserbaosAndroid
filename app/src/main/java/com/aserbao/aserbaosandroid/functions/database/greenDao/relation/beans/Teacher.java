package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: 1142803753@qq.com
 */
@Entity
public class Teacher {
    @Id(autoincrement = true)
    Long id;

    @Unique
    int teacherNo;//职工号

    int age; //年龄
    int sex; //性别

    String name;//姓名
    String schoolName;//学校名字

    String subject;//科目

    @Generated(hash = 2038408037)
    public Teacher(Long id, int teacherNo, int age, int sex, String name,
            String schoolName, String subject) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.age = age;
        this.sex = sex;
        this.name = name;
        this.schoolName = schoolName;
        this.subject = subject;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeacherNo() {
        return this.teacherNo;
    }

    public void setTeacherNo(int teacherNo) {
        this.teacherNo = teacherNo;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }




}
