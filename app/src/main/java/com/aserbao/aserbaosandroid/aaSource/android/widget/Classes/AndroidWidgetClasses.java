package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes;

import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.PopupWindowActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class AndroidWidgetClasses extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("PopupWindow弹出框",PopupWindowActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
