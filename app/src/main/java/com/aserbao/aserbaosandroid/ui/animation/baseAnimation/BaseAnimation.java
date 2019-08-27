package com.aserbao.aserbaosandroid.ui.animation.baseAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class BaseAnimation extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("补间动画",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("帧动画",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("属性动画",2));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }
    }
}
