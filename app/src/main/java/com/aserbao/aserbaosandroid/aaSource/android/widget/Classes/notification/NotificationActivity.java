package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.notification;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class NotificationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(""));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
