package com.aserbao.aserbaosandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.AudioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.base.adapters.BaseActivityAdapter;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.system.SystemActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;
import com.aserbao.aserbaosandroid.ui.constantUtilsShow.ConstantsUtilsShowActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

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
    }

    private void initGetData() {
        mClassBeen.add(new ClassBean("Android", AndroidActivity.class));
        mClassBeen.add(new ClassBean("测试", TestActivity.class));
        mClassBeen.add(new ClassBean("OpenGl", OpenGlActivity.class));
        mClassBeen.add(new ClassBean("策略模式", DesignModeActivity.class));
        mClassBeen.add(new ClassBean("多媒体", AudioAndVideoActivity.class));
        mClassBeen.add(new ClassBean("View", UIActivity.class));
        mClassBeen.add(new ClassBean("功能", FunctionsActivity.class));
        mClassBeen.add(new ClassBean("常用数据", ConstantsUtilsShowActivity.class));
        mClassBeen.add(new ClassBean("系统类", SystemActivity.class));
        mClassBeen.add(new ClassBean("其他", OthersActivity.class));
        mClassBeen.add(new ClassBean("ffmpeg", FFmpegActivity.class));
    }

    private void initView() {
        BaseActivityAdapter adapter = new BaseActivityAdapter(this, this, mClassBeen);
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mHomeRecyclerView.setLayoutManager(gridLayoutManager);
        mHomeRecyclerView.setAdapter(adapter);
    }
}
