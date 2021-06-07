package com.aserbao.aserbaosandroid.aaThird;

import android.view.View;

import com.aserbao.aserbaosandroid.aaThird.dagger2.DaggerActivity;
import com.aserbao.aserbaosandroid.aaThird.okdownload.OkDownLoadActivity;
import com.aserbao.aserbaosandroid.aaThird.okhttp.OkhttpActivity;
import com.aserbao.aserbaosandroid.aaThird.rxJava3.RxJavaActivity;
import com.aserbao.aserbaosandroid.aaThird.videocache.VideoCacheActivity;
import com.aserbao.jetpack.JetpackAct;
import com.aserbao.thirdlibs.ThirdLibActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.database.greenDao.GreenDaoActivity;

public class ThirdActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Dagger2", DaggerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("VideoCache", VideoCacheActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("GreenDao的使用", GreenDaoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("OkDownload的使用", OkDownLoadActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RxJava的使用", RxJavaActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Okhttp的使用", OkhttpActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Jetpack的使用", JetpackAct.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("第三方UI库", ThirdLibActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
