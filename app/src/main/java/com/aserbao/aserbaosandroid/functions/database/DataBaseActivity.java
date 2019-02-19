package com.aserbao.aserbaosandroid.functions.database;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.GreenDaoActivity;
import com.aserbao.aserbaosandroid.functions.database.mySql.MySqlActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class DataBaseActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("SQLite的使用", MySqlActivity.class));
        mClassBeen.add(new ClassBean("GreenDao的使用", GreenDaoActivity.class));
    }
}
