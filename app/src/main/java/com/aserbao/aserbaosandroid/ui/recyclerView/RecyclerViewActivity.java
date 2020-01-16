
package com.aserbao.aserbaosandroid.ui.recyclerView;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.SlideItemAnimationActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest.DoubleRecyclerNestActivity;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView.AddRecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.AnimatorRecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator.ChangeItemAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.MoveToDeleteActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.RecyclerViewPickActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.ScaleSmoothSlideActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.SmoothActivitiy;

public class RecyclerViewActivity extends BaseRecyclerViewActivity {


    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Item侧滑删除", MoveToDeleteActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("交换Item位置动画", ChangeItemAnimatorActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView Item滑动动画处理", SlideItemAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("打造公用的Adapter", MoveToDeleteActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("左右滑动的RecyclerView", ScaleSmoothSlideActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("空白的RecyclerView", AddRecyclerViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("滑动的RecyclerView", SmoothActivitiy.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView滚动冲突处理", RecyclerViewEventActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView嵌套冲突处理", DoubleRecyclerNestActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView动画", AnimatorRecyclerViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView选择器", RecyclerViewPickActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
