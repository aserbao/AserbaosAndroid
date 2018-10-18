package com.aserbao.aserbaosandroid.functions.database.greenDao.relation;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.GreenDaoSimpleActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.toOne.GreenDaoRelationActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class RelationActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("ToOne注释的使用", GreenDaoRelationActivity.class));
    }
}
