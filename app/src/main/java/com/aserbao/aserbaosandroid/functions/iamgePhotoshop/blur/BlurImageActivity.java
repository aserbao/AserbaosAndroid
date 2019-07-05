package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
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
