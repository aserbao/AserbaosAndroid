package com.aserbao.aserbaosandroid.aaSource.android.support;

import com.aserbao.aserbaosandroid.aaSource.android.support.constraint.ConstraintLayoutActvity;
import com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CoordinatorLayout.CoordinatorLayoutActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class SupportActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("ConstraintLayout", ConstraintLayoutActvity.class));
        mClassBeen.add(new ClassBean("CollapsingToolbarLayout", CoordinatorLayoutActivity.class));
    }
}
