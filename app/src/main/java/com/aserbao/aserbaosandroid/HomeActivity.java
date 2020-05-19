package com.aserbao.aserbaosandroid;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.AudioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.lifeCycle.LifeCycleActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels.DataBindingByVMActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle.LifecycleActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.liveData.LiveDataActivity;
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.ViewModelActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
import com.aserbao.aserbaosandroid.kotlin.coroutine.CoroutineActivity;
import com.aserbao.aserbaosandroid.kotlin.scopes.KotlinScopeFunctionsActivity;
import com.aserbao.aserbaosandroid.ui.simpleDraw.SimpleDrawActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure.SoftHeightMeasureActivity;
import com.aserbao.aserbaosandroid.ui.viewPager.ViewPagerActivity;
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
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", CameraXPreviewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", SimpleDrawActivity.class));
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
