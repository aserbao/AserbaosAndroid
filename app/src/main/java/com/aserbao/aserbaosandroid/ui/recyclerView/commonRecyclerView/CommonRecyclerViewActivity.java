package com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.MoveToDeleteActivity;

public class CommonRecyclerViewActivity extends BaseRecyclerViewActivity {
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Item侧滑删除", MoveToDeleteActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
