package com.aserbao.aserbaosandroid.audioAndVideo.detail_Info;

import android.view.View;

import com.aserbao.aserbaosandroid.audioAndVideo.detail_Info.iamgeInfo.ImageInfoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class DetailInfoActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("图片详情", ImageInfoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
