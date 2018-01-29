package com.aserbao.aserbaosandroid.opengl;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.OpenGlRVAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.OneOpenGlActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.BaseCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.FilterCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleOne.CameraOpenglActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OpenGlActivity extends BaseActivity {

    private List<OpenGlBean> mOpenGlBeans = new ArrayList<>();
    @BindView(R.id.opengl_recycler_view)
    RecyclerView mOpenglRecyclerView;
    public OpenGlRVAdapter mOpenGlRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGetData();
        initView();
    }

    @Override
    public int setContentView() {
        return R.layout.activity_open_gl;
    }

    public void initGetData() {
        mOpenGlBeans.add(new OpenGlBean("绘制简单图形", OneOpenGlActivity.class));
        mOpenGlBeans.add(new OpenGlBean("简单相机视图预览", BaseCameraActivity.class));
        mOpenGlBeans.add(new OpenGlBean("Camera+OpenGl显示", CameraOpenglActivity.class));
        mOpenGlBeans.add(new OpenGlBean("给相机添加滤镜", FilterCameraActivity.class));
    }

    public void initView() {
        mOpenGlRVAdapter = new OpenGlRVAdapter(this,this, mOpenGlBeans);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mOpenGlRVAdapter);
    }
}
