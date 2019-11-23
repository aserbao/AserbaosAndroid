
package com.aserbao.aserbaosandroid.ui.recyclerView;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.SlideItemAnimationActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest.DoubleRecyclerNestActivity;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView.AddRecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.AnimatorRecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator.ChangeItemAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.MoveToDeleteActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.RecyclerViewPickActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.ScaleSmoothSlideActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.SmoothActivitiy;

public class RecyclerViewActivity extends BaseActivity {


    public void initGetData() {
        mClassBeen.add(new ClassBean("Item侧滑删除", MoveToDeleteActivity.class));
        mClassBeen.add(new ClassBean("交换Item位置动画", ChangeItemAnimatorActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView Item滑动动画处理", SlideItemAnimationActivity.class));
        mClassBeen.add(new ClassBean("打造公用的Adapter", MoveToDeleteActivity.class));
        mClassBeen.add(new ClassBean("左右滑动的RecyclerView", ScaleSmoothSlideActivity.class));
        mClassBeen.add(new ClassBean("空白的RecyclerView", AddRecyclerViewActivity.class));
        mClassBeen.add(new ClassBean("滑动的RecyclerView", SmoothActivitiy.class));
        mClassBeen.add(new ClassBean("RecyclerView滚动冲突处理", RecyclerViewEventActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView嵌套冲突处理", DoubleRecyclerNestActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView动画", AnimatorRecyclerViewActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView选择器", RecyclerViewPickActivity.class));

    }
}
