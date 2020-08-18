package com.aserbao.aserbaosandroid;

import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.HardwareActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.Camera2SimpleShowSVActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.doubleshow.Camera2VideoGVActivity;
import com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone.CloneTestActivity;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.progressBar.ProgressBarActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.aaThird.okdownload.OkDownLoadActivity;
import com.aserbao.aserbaosandroid.aaThird.rxJava3.api.RxJava3ApiActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.kotlin.KotlinActivity;
import com.aserbao.aserbaosandroid.kotlin.coroutine.CoroutineActivity;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture.OpenGlTextureAct;
import com.aserbao.aserbaosandroid.opengl.videoandcamera.VideoAndCameraShowACT;
import com.aserbao.aserbaosandroid.opengl.videoshow.VideoShowGlSurfaceViewACT;
import com.aserbao.aserbaosandroid.ui.animation.baseAnimation.objectAnimator.ObjectAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.canvas.CanvasActivity;
import com.aserbao.aserbaosandroid.ui.canvas.blendmode.BlendModeActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.font.TextFontActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.simple.SimpleTextViewActivity;
import com.aserbao.common.ui.act.CommonUIActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.base.arouter.RouterConfig;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;
import com.example.camera.act.CameraActivity;

public class HomeActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Android", AndroidActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("第三方库的使用", ThirdActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试", TestActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("OpenGl", OpenGlActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("策略模式", DesignModeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("多媒体", AudioAndVideoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("View", UIActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("功能", FunctionsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("常用数据", AUtilsRealizeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("其他", OthersActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ffmpeg", FFmpegActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("算法", AlgorithmActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Common", CommonUIActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", OpenGlTextureAct.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
