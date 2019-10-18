package com.aserbao.aserbaosandroid.aaSource.android.os.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class HandlerActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("线程间通讯",0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {

    }
}
