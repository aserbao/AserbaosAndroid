package com.aserbao.aserbaosandroid.designMode.singleMode;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class SingleModeActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("懒汉式，线程不安全",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("懒汉式，线程安全",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("饿汉式",3));
        mBaseRecyclerBean.add(new BaseRecyclerBean("双检锁/双重校验锁",4));
        mBaseRecyclerBean.add(new BaseRecyclerBean("静态内部类",5));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 1:
                 Car1.getInstance().drive();
                break;
            case 2:
                 Car2.getInstance().drive();
                break;
            case 3:
                Car3.getInstance().drive();
                break;
            case 4:
                Car4.getSingleton().drive();
                break;
            case 5:
                Car5.getInstance().drive();
                break;
        }
    }
}
