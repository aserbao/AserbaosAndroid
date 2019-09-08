package com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.live;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.media.jCVideoPlayer.play.JCPlayerActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class JCLivePlayerActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CCTV1"));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
//                JCPlayerActivity.lanuch(this,"http://tv.cctv.com/live/cctv1/?date=2019-02-28&index=0");
                JCPlayerActivity.lanuch(this,"http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
                break;
        }
    }
}
