package com.aserbao.aserbaosandroid.functions.database.greenDao;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.GreenDaoRelationActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class GreenDaoActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("GreenDao的基本使用",GreenDaoSimpleActivity.class)) ;
        mClassBeen.add(new ClassBean("GreenDao的多表关联",GreenDaoRelationActivity.class)) ;
    }
}
