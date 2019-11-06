package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ItemARCMAnimationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("add"));
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("remove"));
        for (int i = 0; i < 10; i++) {
            mBaseRecyclerBeen.add(new BaseRecyclerBean(String.valueOf(i)));
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {

    }
}
