package com.aserbao.aserbaosandroid.aaSource.android.hardware;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.Camera2Activity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.Camera2SimpleShowSVActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class HardwareActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Camera2", Camera2Activity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Camera2的简单预览", Camera2SimpleShowSVActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
