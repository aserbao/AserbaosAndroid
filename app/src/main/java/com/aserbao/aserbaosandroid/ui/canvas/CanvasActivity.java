package com.aserbao.aserbaosandroid.ui.canvas;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.CanvasPropertyActivity;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixActivity;
import com.aserbao.aserbaosandroid.ui.canvas.shadows.ShadowsActivity;

public class CanvasActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Canvas性能检测",CanvasPropertyActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Matrix",MatrixActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("阴影", ShadowsActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
