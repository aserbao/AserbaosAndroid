package com.aserbao.aserbaosandroid.audioAndVideo.media.image2video;

import com.aserbao.aserbaosandroid.audioAndVideo.media.image2video.jcodec.JCodecImage2VideoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

/**
 * 图片转视频
 */
public class Image2VideoActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("通过JCodec将图片生成视频", JCodecImage2VideoActivity.class));
    }
}
