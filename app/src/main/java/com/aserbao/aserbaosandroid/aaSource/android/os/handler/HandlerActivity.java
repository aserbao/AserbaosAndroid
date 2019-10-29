package com.aserbao.aserbaosandroid.aaSource.android.os.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.os.handler.download.HandlerCommunicationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.os.handler.principle_for_handler.PrincipleHanlderActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class HandlerActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Handler线程间通信", HandlerCommunicationActivity.class,0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Handler原理", PrincipleHanlderActivity.class,1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {}
}
