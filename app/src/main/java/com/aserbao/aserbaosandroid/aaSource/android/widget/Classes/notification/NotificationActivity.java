package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

public class NotificationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(""));
    }

    @Override
    public void itemClickBack(View view, int position) {

    }
}
