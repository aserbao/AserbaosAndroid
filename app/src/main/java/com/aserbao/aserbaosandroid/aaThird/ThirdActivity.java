package com.aserbao.aserbaosandroid.aaThird;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.okdownload.OkDownLoadActivity;
import com.aserbao.aserbaosandroid.aaThird.okhttp.OkhttpActivity;
import com.aserbao.aserbaosandroid.aaThird.rxJava3.RxJavaActivity;
import com.aserbao.aserbaosandroid.aaThird.videocache.VideoCacheActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.database.greenDao.GreenDaoActivity;

public class ThirdActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Dagger2", DaggerActivity.class));
        mClassBeen.add(new ClassBean("VideoCache", VideoCacheActivity.class));
        mClassBeen.add(new ClassBean("GreenDao的使用", GreenDaoActivity.class));
        mClassBeen.add(new ClassBean("OkDownload的使用", OkDownLoadActivity.class));
        mClassBeen.add(new ClassBean("RxJava的使用", RxJavaActivity.class));
        mClassBeen.add(new ClassBean("Okhttp的使用", OkhttpActivity.class));
    }
}
