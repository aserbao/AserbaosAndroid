package com.aserbao.aserbaosandroid.AudioAndVideo.detail_Info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.AudioAndVideo.detail_Info.iamgeInfo.ImageInfoActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class DetailInfoActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("图片详情", ImageInfoActivity.class));
    }
}
