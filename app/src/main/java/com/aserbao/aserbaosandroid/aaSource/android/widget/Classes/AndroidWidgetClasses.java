package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.PopupWindowActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AndroidWidgetClasses extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("PopupWindow弹出框",PopupWindowActivity.class));
    }
}
