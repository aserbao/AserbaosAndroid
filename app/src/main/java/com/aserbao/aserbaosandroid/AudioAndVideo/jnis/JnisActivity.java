package com.aserbao.aserbaosandroid.AudioAndVideo.jnis;

import com.aserbao.aserbaosandroid.AudioAndVideo.jnis.javaCallC.JavaCallCActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class JnisActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Java调C代码实现", JavaCallCActivity.class));
    }
}
