package com.aserbao.aserbaosandroid.algorithm;

import android.view.View;

import com.aserbao.aserbaosandroid.algorithm.list.AboutListSortActivty;
import com.aserbao.aserbaosandroid.algorithm.sort.SortAlgorithmActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class AlgorithmActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("排序算法", SortAlgorithmActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("List排序算法", AboutListSortActivty.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
