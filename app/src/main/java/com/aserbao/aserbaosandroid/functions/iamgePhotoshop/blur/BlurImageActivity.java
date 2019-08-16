package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.mmin18.MMin18Activity;

public class BlurImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("EasyBlur高斯模糊",ImageBlurActivity.class));
        mClassBeen.add(new ClassBean("动态高斯模糊", MMin18Activity.class));
    }

}
