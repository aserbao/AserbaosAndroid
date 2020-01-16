package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow;

import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.demo.PopupWindowSimpleDemo;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class PopupWindowActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("SimpleDemo", PopupWindowSimpleDemo.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
