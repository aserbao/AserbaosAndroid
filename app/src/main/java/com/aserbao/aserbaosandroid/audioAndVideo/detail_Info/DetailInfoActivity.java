package com.aserbao.aserbaosandroid.audioAndVideo.detail_Info;

import com.aserbao.aserbaosandroid.audioAndVideo.detail_Info.iamgeInfo.ImageInfoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class DetailInfoActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("图片详情", ImageInfoActivity.class));
    }
}
