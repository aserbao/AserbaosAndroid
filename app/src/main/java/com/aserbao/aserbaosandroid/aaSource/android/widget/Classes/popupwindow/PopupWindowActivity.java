package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.demo.PopupWindowSimpleDemo;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class PopupWindowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("SimpleDemo", PopupWindowSimpleDemo.class));
    }
}
