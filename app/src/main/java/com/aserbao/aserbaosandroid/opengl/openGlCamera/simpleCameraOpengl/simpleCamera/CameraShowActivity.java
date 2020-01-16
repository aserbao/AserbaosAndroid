package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CameraShowActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("SurfaceView显示Camera数据",CameraSurfaceViewShowActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("TextureView显示Camera数据",CameraTextureViewShowActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("GlSurfaceView显示Camera数据",CameraGlSurfaceShowActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("SurfaceTexture显示Camera数据",CameraSurfaceTextureShowActivity.class));
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
