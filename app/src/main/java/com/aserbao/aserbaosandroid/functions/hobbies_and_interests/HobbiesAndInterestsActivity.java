package com.aserbao.aserbaosandroid.functions.hobbies_and_interests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.urlAnalyze.UrlAnalyzeActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class HobbiesAndInterestsActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("链接解析",UrlAnalyzeActivity.class));
    }
}
