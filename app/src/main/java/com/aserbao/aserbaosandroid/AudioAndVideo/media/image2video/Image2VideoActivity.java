package com.aserbao.aserbaosandroid.AudioAndVideo.media.image2video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.AudioAndVideo.media.image2video.jcodec.JCodecImage2VideoActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

/**
 * 图片转视频
 */
public class Image2VideoActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("通过JCodec将图片生成视频", JCodecImage2VideoActivity.class));
    }
}
