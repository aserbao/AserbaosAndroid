package com.aserbao.aserbaosandroid.functions.aboutCompression;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CompressionActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("zip的压缩解压",AboutZipActivity.class,0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
     }
}
