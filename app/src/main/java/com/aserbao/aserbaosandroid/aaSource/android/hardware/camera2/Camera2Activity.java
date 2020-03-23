package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.Camera2SimpleShowSVActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class Camera2Activity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Camera2超简单预览", Camera2SimpleShowSVActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
