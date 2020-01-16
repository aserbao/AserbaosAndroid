package com.aserbao.aserbaosandroid;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.ScalpelFrameLayout;
import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.beans.VHSeekBarBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;
import com.aserbao.aserbaosandroid.ui.buttons.switchButton.SwitchButtonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseRecyclerViewActivity {

    /*@BindView(R.id.container)
    ScalpelFrameLayout mContainer;
    @BindView(R.id.home_recycler_view)
    RecyclerView mHomeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initGetData();
    }*/
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
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", SwitchButtonActivity.class));
    }
    

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        
    }
}
