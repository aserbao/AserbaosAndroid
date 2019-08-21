package com.aserbao.aserbaosandroid.functions.iamgePhotoshop;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.BlurImageActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.compound.CompoundActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class ImagePhotoShopActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("图片高斯模糊", BlurImageActivity.class));
        mClassBeen.add(new ClassBean("图片合成",CompoundActivity.class));
    }
}
