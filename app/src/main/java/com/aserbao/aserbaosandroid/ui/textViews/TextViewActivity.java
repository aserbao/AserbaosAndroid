package com.aserbao.aserbaosandroid.ui.textViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.textViews.shadow.TextShadowActivity;

public class TextViewActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("textView的阴影效果", TextShadowActivity.class));
    }
}
