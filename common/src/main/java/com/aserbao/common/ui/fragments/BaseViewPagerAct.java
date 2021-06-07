package com.aserbao.common.ui.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * @Created time:2021/6/7 2:24 PM
 * @author: aserbao
 * @description:
 **/
public class BaseViewPagerAct extends AppCompatActivity {

    private ViewPager mBaseViewPager;
    private String[] mTitles;

    public EmptyFragmentAdapter setViewPager(ViewPager viewPager,String[] titles){
        mBaseViewPager = viewPager;
        mTitles = titles;
        EmptyFragmentAdapter emptyFragmentAdapter = new EmptyFragmentAdapter(getSupportFragmentManager(),mTitles);
        mBaseViewPager.setAdapter(emptyFragmentAdapter);
        return emptyFragmentAdapter;
    }


}
