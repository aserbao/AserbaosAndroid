package com.aserbao.aserbaosandroid.aaSource.android.os.handler;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.os.handler.download.HandlerCommunicationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.os.handler.principle_for_handler.PrincipleHanlderActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class HandlerActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Handler线程间通信", HandlerCommunicationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Handler原理", PrincipleHanlderActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
