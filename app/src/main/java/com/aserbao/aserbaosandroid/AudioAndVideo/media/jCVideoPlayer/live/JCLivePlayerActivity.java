package com.aserbao.aserbaosandroid.AudioAndVideo.media.jCVideoPlayer.live;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.AudioAndVideo.media.jCVideoPlayer.play.JCPlayerActivity;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

public class JCLivePlayerActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CCTV1"));
    }

    @Override
    public void itemClickBack(int position) {
        switch (position){
            case 0:
//                JCPlayerActivity.lanuch(this,"http://tv.cctv.com/live/cctv1/?date=2019-02-28&index=0");
                JCPlayerActivity.lanuch(this,"http://ivi.bupt.edu.cn/hls/cctv1.m3u8");
                break;
        }
    }
}
