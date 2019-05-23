package com.aserbao.aserbaosandroid.aaThird.dagger2;

import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.buttons.ButtonActivity;

import javax.inject.Inject;

public class DaggerActivity extends BaseRecyclerViewActivity {



    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("不使用Dagger2的做法"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Dagger2"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("跳"));
    }

    private static final String TAG = "DaggerActivity";
    @Override
    public void itemClickBack(View view , int position) {
        switch (position){
            case 0:

                break;
            case 1:

                break;
            case 2:
                ButtonActivity.launch(this);
                break;
        }

    }
}
