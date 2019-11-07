package com.aserbao.aserbaosandroid.ui.customView;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.path.PathView;
import com.aserbao.aserbaosandroid.ui.canvas.path.PathView7;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.Bezier2;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.Bezier3;
import com.aserbao.aserbaosandroid.ui.customView.bezier.canDrag.BezierCircle;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.BezierCustomLike;
import com.aserbao.aserbaosandroid.ui.customView.customImageView.PointImageView;
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
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Path的使用2",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("PathInterpator动画",8));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带红点的ImageView",9));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
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
                PathView7 pathView = new PathView7(this);
                mBaseRecyclerEmptyContainer.addView(pathView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 7:
                PathView pathView1 = new PathView(this);
                mBaseRecyclerEmptyContainer.addView(pathView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case 8:
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.emoji_00);
                int dp24 = DisplayUtil.dip2px(24);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dp24, dp24);
//                layoutParams.height = DisplayUtil.dip2px(24);
//                layoutParams.width = DisplayUtil.dip2px(24);
                imageView.setLayoutParams(layoutParams);
                mBaseRecyclerEmptyContainer.addView(imageView);


                Path path = new Path();
                path.moveTo(0,0);
                int screenWidth = AserbaoApplication.screenWidth/2;
                int screenHeight = AserbaoApplication.screenHeight/2;
                path.lineTo(screenWidth /2, screenHeight);
                path.lineTo(screenWidth,screenHeight/2);
//                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.X, View.Y, path);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();


                break;
            case 9:
                PointImageView pointImageView = new PointImageView(this);
                pointImageView.setImageResource(R.drawable.emoji_00);
                pointImageView.setMessageNum(10);
                pointImageView.setPointMode(PointImageView.NUMBER_POINT);
                pointImageView.setHaveMesage(true);
                mBaseRecyclerEmptyContainer.addView(pointImageView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
        }
    }
}
