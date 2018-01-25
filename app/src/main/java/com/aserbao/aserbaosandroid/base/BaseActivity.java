package com.aserbao.aserbaosandroid.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*基类*/
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        initGetData();
        initView();
    }

    public abstract  int setContentView();

    public abstract void initGetData() ;

    public abstract void initView();
}
