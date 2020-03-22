package com.aserbao.aserbaosandroid.audioAndVideo.jnis;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.jnis.javaCallC.JavaCallCActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class JnisActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Java调C代码实现", JavaCallCActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
