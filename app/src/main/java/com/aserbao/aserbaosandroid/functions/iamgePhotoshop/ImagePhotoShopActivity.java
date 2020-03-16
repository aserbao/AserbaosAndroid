package com.aserbao.aserbaosandroid.functions.iamgePhotoshop;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.BlurImageActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.compound.CompoundActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ImagePhotoShopActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("图片高斯模糊", BlurImageActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("图片合成",CompoundActivity.class));
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
