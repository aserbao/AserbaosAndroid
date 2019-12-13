package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CameraShowActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("SurfaceView显示Camera数据",CameraSurfaceViewShowActivity.class,0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("TextureView显示Camera数据",CameraTextureViewShowActivity.class,1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("GlSurfaceView显示Camera数据",CameraGlSurfaceShowActivity.class,2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("SurfaceTexture显示Camera数据",CameraSurfaceTextureShowActivity.class,3));
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
