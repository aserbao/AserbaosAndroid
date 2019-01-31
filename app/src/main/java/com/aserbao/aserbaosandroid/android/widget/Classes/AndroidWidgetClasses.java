package com.aserbao.aserbaosandroid.android.widget.Classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.PopupWindowActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

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
