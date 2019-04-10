package com.aserbao.aserbaosandroid.ui.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.customView.radar.RadarView;

public class CustomViewActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        setLinearLayoutOrientationHorizontal();
        mBaseRecyclerBeen.add(new BaseRecyclerBean("雷达"));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                RadarView radarView = new RadarView(this);
                radarView.setGradient(new int[2]);
                radarView.startScan();
                mBaseRecyclerEmptyContainer.addView(radarView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
        }
    }
}
