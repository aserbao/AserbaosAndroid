package com.aserbao.aserbaosandroid.functions.iamgePhotoshop;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.BlurImageActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.ImageBlurActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.compound.CompoundActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class ImagePhotoShopActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("图片高斯模糊", BlurImageActivity.class));
        mClassBeen.add(new ClassBean("图片合成",CompoundActivity.class));
    }
}
