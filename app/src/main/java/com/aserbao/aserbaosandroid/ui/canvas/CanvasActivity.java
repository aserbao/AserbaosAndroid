package com.aserbao.aserbaosandroid.ui.canvas;

import android.graphics.Color;
import android.view.View;

import com.aserbao.aserbaosandroid.ui.canvas.blendmode.BlendModeActivity;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.ARadiusView;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.ProgressView;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.CanvasPropertyActivity;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixActivity;
import com.aserbao.aserbaosandroid.ui.canvas.shadows.ShadowsActivity;
import com.example.base.utils.screen.DisplayUtil;

public class CanvasActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("混合模式", BlendModeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Canvas性能检测",CanvasPropertyActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Matrix",MatrixActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("阴影", ShadowsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("canvas渐变", 0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
//                handleShape();
                addCoverView();
                break;
        }
    }


    public void handleShape(){
        ProgressView progressView = new ProgressView(this);
        addViewHToFl(progressView,false,false,DisplayUtil.getScreenWidth(this), DisplayUtil.dp2px(50),false);
        progressView.changeDuration(10*1000);
    }

    public void addCoverView(){
        ARadiusView coverView = new ARadiusView(this);
        coverView.setBackgroundColor(Color.BLACK);
        addViewHToFl(coverView,false,false,DisplayUtil.getScreenWidth(this), DisplayUtil.dp2px(50),false);
    }
}
