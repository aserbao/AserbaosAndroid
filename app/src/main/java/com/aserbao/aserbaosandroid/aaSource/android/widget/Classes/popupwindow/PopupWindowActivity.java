package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.demo.PopupWindowSimpleDemo;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

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
