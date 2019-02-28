package com.aserbao.aserbaosandroid.aaSource.android;

import com.aserbao.aserbaosandroid.aaSource.android.app.AppActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.MaterialActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class AndroidActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("App", AppActivity.class));
        mClassBeen.add(new ClassBean("Material", MaterialActivity.class));
    }
}
