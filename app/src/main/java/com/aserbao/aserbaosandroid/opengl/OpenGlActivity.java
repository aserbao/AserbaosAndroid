package com.aserbao.aserbaosandroid.opengl;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.CommonAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.OneOpenGlActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.RecordCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera.CameraShowActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera.CameraSurfaceViewShowActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.FilterCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleOne.CameraOpenglActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OpenGlActivity extends BaseActivity {



    @Override
    public int setContentView() {
        return R.layout.activity_open_gl;
    }

    public void initGetData() {
        mClassBeen.add(new ClassBean("绘制简单图形", OneOpenGlActivity.class));
        mClassBeen.add(new ClassBean("简单相机视图预览", CameraShowActivity.class));
        mClassBeen.add(new ClassBean("Camera+OpenGl显示", CameraOpenglActivity.class));
        mClassBeen.add(new ClassBean("相机录制", RecordCameraActivity.class));
        mClassBeen.add(new ClassBean("给相机添加滤镜", FilterCameraActivity.class));
    }

}
