package com.aserbao.aserbaosandroid.other;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.other.compare.CompareActivity;
import com.aserbao.aserbaosandroid.other.valuePass.ValuePassActivity;

public class OthersActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("集合排序", CompareActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Java中的传递", ValuePassActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
