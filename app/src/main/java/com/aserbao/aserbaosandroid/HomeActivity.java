package com.aserbao.aserbaosandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.ScalpelFrameLayout;
import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.audioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.system.SystemActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;
import com.aserbao.aserbaosandroid.ui.animation.baseAnimation.objectAnimator.ObjectAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.animation.baseAnimation.tweenAnimator.TweenAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.simple.SimpleTextViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ScalpelFrameLayout mContainer;
    private List<ClassBean> mClassBeen = new ArrayList<>();
    @BindView(R.id.home_recycler_view)
    RecyclerView mHomeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initGetData();
        initView();
        mContainer.setLayerInteractionEnabled(false);
    }

    private void initGetData() {
        mClassBeen.add(new ClassBean("Android", AndroidActivity.class));
        mClassBeen.add(new ClassBean("第三方库的使用", ThirdActivity.class));
        mClassBeen.add(new ClassBean("测试", TestActivity.class));
        mClassBeen.add(new ClassBean("OpenGl", OpenGlActivity.class));
        mClassBeen.add(new ClassBean("策略模式", DesignModeActivity.class));
        mClassBeen.add(new ClassBean("多媒体", AudioAndVideoActivity.class));
        mClassBeen.add(new ClassBean("View", UIActivity.class));
        mClassBeen.add(new ClassBean("功能", FunctionsActivity.class));
        mClassBeen.add(new ClassBean("常用数据", AUtilsRealizeActivity.class));
        mClassBeen.add(new ClassBean("系统类", SystemActivity.class));
        mClassBeen.add(new ClassBean("其他", OthersActivity.class));
        mClassBeen.add(new ClassBean("ffmpeg", FFmpegActivity.class));
        mClassBeen.add(new ClassBean("算法", AlgorithmActivity.class));
        mClassBeen.add(new ClassBean("当前调试的界面", AShareModuleActivity.class));
    }

    private void initView() {
        BaseActivityAdapter adapter = new BaseActivityAdapter(this, this, mClassBeen);
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mHomeRecyclerView.setLayoutManager(gridLayoutManager);
        mHomeRecyclerView.setAdapter(adapter);
    }
}
