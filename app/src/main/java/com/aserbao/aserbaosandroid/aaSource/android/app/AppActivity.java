package com.aserbao.aserbaosandroid.aaSource.android.app;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.ActivityClass;
import com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.FragmentActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class AppActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity", ActivityClass.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Fragment", FragmentActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
