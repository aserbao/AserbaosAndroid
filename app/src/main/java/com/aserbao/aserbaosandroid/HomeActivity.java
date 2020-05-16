package com.aserbao.aserbaosandroid;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.lifeCycle.LifeCycleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.bottomSheetDialog.BottomSheetDialogActivity;
import com.aserbao.aserbaosandroid.aaSource.android.support.constraint.ConstraintLayoutActvity;
import com.aserbao.aserbaosandroid.aaSource.android.view.Choreographer.ChoreographerActivity;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.GridView.GridViewActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.JetpackActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels.DataBindingByVMActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle.LifecycleActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.liveData.LiveDataActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.ViewModelActivity;
import com.aserbao.aserbaosandroid.aaThird.okdownload.OkDownLoadActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediaplayer.MediaPlayerActivity;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.createBitmap.CreateBitmapActivity;
import com.aserbao.aserbaosandroid.functions.cutout.CutOutActivity;
import com.aserbao.aserbaosandroid.ui.canvas.blendmode.BlendModeActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure.SoftHeightMeasureActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;
import com.example.camera.CameraActivity;
import com.example.camera.camerax.CameraXPreviewActivity;

public class HomeActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "HomeActivity";

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
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", ChoreographerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试", 0));


    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e(TAG, "onLoadFailed() called with: e = [" + e + "], model = [" + model + "], target = [" + target + "], isFirstResource = [" + isFirstResource + "]");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.e(TAG, "onResourceReady() called with: resource = [" + resource + "], model = [" + model + "], target = [" + target + "], dataSource = [" + dataSource + "], isFirstResource = [" + isFirstResource + "]");
                        return false;
                    }
                });
                break;
        }
    }
}
