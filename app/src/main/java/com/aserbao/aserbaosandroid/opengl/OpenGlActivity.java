package com.aserbao.aserbaosandroid.opengl;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.OneOpenGlActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.RecordCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera.CameraShowActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.FilterCameraActivity;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleOne.CameraOpenglActivity;

public class OpenGlActivity extends BaseRecyclerViewActivity {


    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("绘制简单图形", OneOpenGlActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("简单相机视图预览", CameraShowActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Camera+OpenGl显示", CameraOpenglActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("相机录制", RecordCameraActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("给相机添加滤镜", FilterCameraActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
