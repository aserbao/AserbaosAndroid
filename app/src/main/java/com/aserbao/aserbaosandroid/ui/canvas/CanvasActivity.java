package com.aserbao.aserbaosandroid.ui.canvas;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.CanvasPropertyActivity;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixActivity;
import com.aserbao.aserbaosandroid.ui.canvas.shadows.ShadowsActivity;

public class CanvasActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Canvas性能检测",CanvasPropertyActivity.class));
        mClassBeen.add(new ClassBean("Matrix",MatrixActivity.class));
        mClassBeen.add(new ClassBean("阴影", ShadowsActivity.class));
    }
}
