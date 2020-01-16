package com.aserbao.aserbaosandroid.ui;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.animation.AnimationActivity;
import com.aserbao.aserbaosandroid.ui.canvas.CanvasActivity;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;
import com.aserbao.aserbaosandroid.ui.customView.CustomViewActivity;
import com.aserbao.aserbaosandroid.ui.imageviews.ImageViewsActivity;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.RandomAndNoOverLayActivity;
import com.aserbao.aserbaosandroid.ui.texts.TextsActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.RecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.simpleDraw.SimpleDrawActivity;
import com.aserbao.aserbaosandroid.ui.toasts.ToastActivity;

public class UIActivity extends BaseRecyclerViewActivity {



    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Canvas这边请", CanvasActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("随机生成不重叠的圆", RandomAndNoOverLayActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("动画效果", AnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("自定义View", CustomViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Android自定义绘制这边走", SimpleDrawActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView的使用", RecyclerViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("文本视图的使用", TextsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ColorPicker取色器的使用", ColorPickerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Toast的使用", ToastActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ImageView的使用", ImageViewsActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
