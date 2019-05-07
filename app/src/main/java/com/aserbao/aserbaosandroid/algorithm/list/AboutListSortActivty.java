package com.aserbao.aserbaosandroid.algorithm.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

import java.util.ArrayList;
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
        mBaseRecyclerBeen.add(new BaseRecyclerBean("随机添加一名学生"));
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
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                Student student = new Student(RandomValue.getChineseName(), random.nextInt(5) + 15, random.nextInt(5) + 80);
                studentAdapters.addStudent(student);
                break;
        }
    }
}
