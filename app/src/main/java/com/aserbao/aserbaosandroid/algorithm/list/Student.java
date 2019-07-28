package com.aserbao.aserbaosandroid.algorithm.list;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/7 8:12 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.algorithm.list
 */
public class Student {
    String user_name;
    int age;
    int score;

    public Student(String user_name, int age, int score) {
        this.user_name = user_name;
        this.age = age;
        this.score = score;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
            "user_name='" + user_name + '\'' +
            ", age=" + age +
            ", score=" + score +
            '}';
    }
}
