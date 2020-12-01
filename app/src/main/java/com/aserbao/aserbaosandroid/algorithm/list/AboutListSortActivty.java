package com.aserbao.aserbaosandroid.algorithm.list;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;

import com.example.base.utils.random.RandomValue;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合排序
 */
public class AboutListSortActivty extends BaseRecyclerViewActivity {
    private Random random;
    private StudentAdapters studentAdapters;

    @Override
    public void initGetData() {
        initViews();
        mBaseRecyclerBean.add(new BaseRecyclerBean("随机添加一名学生"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("List中两个相邻数据互换位置"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("List中set和remove方法"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("List中addAll方法",3));
    }

    private void initViews() {
        random = new Random();
        studentAdapters = new StudentAdapters(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mShowDataContentRv.setLayoutManager(linearLayoutManager);
        mShowDataContentRv.setAdapter(studentAdapters);
        mShowDataContentRv.setVisibility(View.VISIBLE);
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                Student student = new Student(RandomValue.getChineseName(), random.nextInt(5) + 15, random.nextInt(5) + 80);
//                studentAdapters.addStudent(student);
                break;
            case 1:
                List<Student> mStudentList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Student students = new Student(RandomValue.getChineseName(), random.nextInt(5) + 15, i+ 80);
                    mStudentList.add(students);
                }
                Collections.swap(mStudentList,0,6);
                studentAdapters.addStudentList(mStudentList);
                break;
            case 2:
                List<Student> studentList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Student students = new Student(RandomValue.getChineseName(), random.nextInt(5) + 15, i+ 80);
                    studentList.add(students);
                }
                studentList.set(1,new Student(RandomValue.getChineseName(), random.nextInt(5) + 15, 100));
                studentList.remove(studentList.size()-1);
                studentAdapters.addStudentList(studentList);
                break;
            case 3:
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    arrayList.add(String.valueOf(i));
                    arrayList2.add(String.valueOf(i));
                }
                arrayList.addAll(1,arrayList2);
                Log.d(TAG, "itemClickBack() called with: view = [" + view + "], position = [" + position + "], isLongClick = [" + isLongClick + "], comeFrom = [" + comeFrom + "]");
                break;
        }
    }
}
