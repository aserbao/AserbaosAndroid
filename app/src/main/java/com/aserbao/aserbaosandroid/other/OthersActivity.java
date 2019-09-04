package com.aserbao.aserbaosandroid.other;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.other.compare.CompareActivity;
import com.aserbao.aserbaosandroid.other.valuePass.ValuePassActivity;

public class OthersActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("集合排序", CompareActivity.class));
        mClassBeen.add(new ClassBean("Java中的传递", ValuePassActivity.class));
    }
}
