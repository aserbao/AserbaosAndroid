package com.aserbao.aserbaosandroid.ui.animation.viewAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.ui.customView.radar.RadarView;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.screen.DisplayUtil;

/**
* @Created time:2021/6/8 4:05 PM
* @author: aserbao
* @description: View的动画处理
**/
public class ViewAnimationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("喜欢不喜欢动画",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("向右展开动画",2));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position) {
            case 1:
                SmileView smileView = new SmileView(this);
                mBaseRecyclerEmptyContainer.addView(smileView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 2:
                SwitchAnimationView switchAnimationView = new SwitchAnimationView(this);
                int dp40 = DisplayUtil.dip2px(40);
                mBaseRecyclerEmptyContainer.addView(switchAnimationView,dp40,dp40);
                break;
        }
    }
}