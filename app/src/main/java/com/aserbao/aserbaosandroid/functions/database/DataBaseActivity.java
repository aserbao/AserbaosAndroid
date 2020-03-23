package com.aserbao.aserbaosandroid.functions.database;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.GreenDaoActivity;
import com.aserbao.aserbaosandroid.functions.database.mySql.MySqlActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class DataBaseActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("SQLite的使用", MySqlActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("GreenDao的使用", GreenDaoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
