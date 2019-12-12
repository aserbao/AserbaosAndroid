package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.DecodeShowVideoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class MediaCodecActivity extends BaseRecyclerViewActivity {


    public static final int INT_ENCODE = 0;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("MediaCodec解码本地视频到SurfaceView上显示", DecodeShowVideoActivity.class,INT_ENCODE));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
