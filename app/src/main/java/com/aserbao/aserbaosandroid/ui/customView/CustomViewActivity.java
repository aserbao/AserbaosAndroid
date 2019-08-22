package com.aserbao.aserbaosandroid.ui.customView;

import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.path.PathView;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.Bezier2;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.Bezier3;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.BezierCircle;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.BezierCustomLike;
import com.aserbao.aserbaosandroid.ui.customView.radar.RadarView;

public class CustomViewActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("移除"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("雷达"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("二阶贝塞尔曲线"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("三阶贝塞尔曲线"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("通过贝塞尔曲线绘制一个圆"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("点赞动画"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Path的使用"));
    }

    @Override
    public void itemClickBack(View view, int position) {
        mBaseRecyclerEmptyContainer.removeAllViews();
        switch (position){
            case 1:
                RadarView radarView = new RadarView(this);
                radarView.setGradient(new int[2]);
                radarView.startScan();
                mBaseRecyclerEmptyContainer.addView(radarView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 2:
                Bezier2 bezier = new Bezier2(this);
                mBaseRecyclerEmptyContainer.addView(bezier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 3:
                Bezier3 bezier3 = new Bezier3(this);
                mBaseRecyclerEmptyContainer.addView(bezier3, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 4:
                BezierCircle bezier4 = new BezierCircle(this);
                mBaseRecyclerEmptyContainer.addView(bezier4, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 5:
                BezierCustomLike bezierCustomLike = new BezierCustomLike(this);
                bezierCustomLike.setClickable(true);
                mBaseRecyclerEmptyContainer.addView(bezierCustomLike, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 6:
                PathView pathView = new PathView(this);
                mBaseRecyclerEmptyContainer.addView(pathView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;

        }
    }
}
