package com.aserbao.aserbaosandroid.functions.hobbies_and_interests;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.ADYUrlActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.urlAnalyze.UrlAnalyzeActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class HobbiesAndInterestsActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("链接解析1",UrlAnalyzeActivity.class));
        mClassBeen.add(new ClassBean("链接解析2",ADYUrlActivity.class));
    }
}
