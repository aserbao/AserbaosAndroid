package com.aserbao.aserbaosandroid.functions.database.greenDao;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.RelationActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class GreenDaoActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("GreenDao的基本使用",GreenDaoSimpleActivity.class)) ;
        mClassBeen.add(new ClassBean("GreenDao的关系使用",RelationActivity.class)) ;
    }
}
