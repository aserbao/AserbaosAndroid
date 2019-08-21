package com.aserbao.aserbaosandroid.ui.toasts;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ACustomToastActivity;

public class ToastActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("自定义的Toast使用",ACustomToastActivity.class));
    }
}
