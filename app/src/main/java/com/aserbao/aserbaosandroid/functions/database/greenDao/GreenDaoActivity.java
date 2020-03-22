package com.aserbao.aserbaosandroid.functions.database.greenDao;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.GreenDaoRelationActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class GreenDaoActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("GreenDao的基本使用",GreenDaoSimpleActivity.class)) ;
        mBaseRecyclerBean.add(new BaseRecyclerBean("GreenDao的多表关联",GreenDaoRelationActivity.class)) ;
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}

}
