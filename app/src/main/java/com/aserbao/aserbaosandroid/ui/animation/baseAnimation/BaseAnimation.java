package com.aserbao.aserbaosandroid.ui.animation.baseAnimation;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class BaseAnimation extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("补间动画",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("帧动画",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("属性动画",2));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
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
