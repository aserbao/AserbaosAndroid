package com.aserbao.aserbaosandroid.audioAndVideo.jnis;

import com.aserbao.aserbaosandroid.audioAndVideo.jnis.javaCallC.JavaCallCActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class JnisActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Java调C代码实现", JavaCallCActivity.class));
    }
}
