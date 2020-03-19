package com.aserbao.aserbaosandroid.functions.database.room.beans;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
@Entity(tableName = "student",indices = {@Index(value = "student_no",unique = true)})
public class StudentRoom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;

    @ColumnInfo(name = "student_no")
    public int studentNo;//学号
    @ColumnInfo(name = "age")
    public int age; //年龄
    @ColumnInfo(name = "tel_phone")
    public String telPhone;//手机号
    public String sex; //性别
    public String name;//姓名
    public String address;//家庭住址
    @ColumnInfo(name = "school_name")
    public String schoolName;//学校名字
    public String grade;//几年级


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentRoom{" +
            "id=" + id +
            ", studentNo=" + studentNo +
            ", age=" + age +
            ", telPhone='" + telPhone + '\'' +
            ", sex='" + sex + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", schoolName='" + schoolName + '\'' +
            ", grade='" + grade + '\'' +
            '}';
    }
}
