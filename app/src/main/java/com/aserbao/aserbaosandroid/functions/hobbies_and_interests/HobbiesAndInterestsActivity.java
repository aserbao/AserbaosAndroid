package com.aserbao.aserbaosandroid.functions.hobbies_and_interests;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.ADYUrlActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.urlAnalyze.UrlAnalyzeActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class HobbiesAndInterestsActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("链接解析1",UrlAnalyzeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("链接解析2",ADYUrlActivity.class));
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
