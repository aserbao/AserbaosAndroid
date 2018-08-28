package com.aserbao.aserbaosandroid.ui.toasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ACustomToastActivity;

public class ToastActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("自定义的Toast使用",ACustomToastActivity.class));
    }
}
