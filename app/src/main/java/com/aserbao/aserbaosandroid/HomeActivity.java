package com.aserbao.aserbaosandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;

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
        mClassBeen.add(new ClassBean("OpenGl", OpenGlActivity.class));
        mClassBeen.add(new ClassBean("策略模式", DesignModeActivity.class));
    }

    private void initView() {
        CommonAdapter adapter = new CommonAdapter(this, this, mClassBeen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mHomeRecyclerView.setLayoutManager(linearLayoutManager);
        mHomeRecyclerView.setAdapter(adapter);
    }
}
