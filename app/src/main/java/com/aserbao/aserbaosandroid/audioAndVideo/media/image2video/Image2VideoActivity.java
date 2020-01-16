package com.aserbao.aserbaosandroid.audioAndVideo.media.image2video;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.media.image2video.jcodec.JCodecImage2VideoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/**
 * 图片转视频
 */
public class Image2VideoActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过JCodec将图片生成视频", JCodecImage2VideoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
