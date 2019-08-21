package com.aserbao.aserbaosandroid.algorithm;

import com.aserbao.aserbaosandroid.algorithm.list.AboutListSortActivty;
import com.aserbao.aserbaosandroid.algorithm.sort.SortAlgorithmActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AlgorithmActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("排序算法", SortAlgorithmActivity.class));
        mClassBeen.add(new ClassBean("List排序算法", AboutListSortActivty.class));
    }
}
