package com.aserbao.aserbaosandroid.audioAndVideo;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.detail_Info.DetailInfoActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.jnis.JnisActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.MediaActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class AudioAndVideoActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("多媒体详情", DetailInfoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("播放器", MediaActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("JNI", JnisActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
