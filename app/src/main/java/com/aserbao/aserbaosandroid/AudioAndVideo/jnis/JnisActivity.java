package com.aserbao.aserbaosandroid.AudioAndVideo.jnis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.AudioAndVideo.jnis.javaCallC.JavaCallCActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class JnisActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Java调C代码实现", JavaCallCActivity.class));
    }
}
