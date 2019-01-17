package com.aserbao.aserbaosandroid.ui.canvas;

import android.support.v7.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixActivity;

public class CanvasActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Matrix",MatrixActivity.class));
    }
}
