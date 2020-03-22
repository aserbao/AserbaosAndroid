package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.officalTransition.AActivityOptionsActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.transition.TransitionActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition.AAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byTheme.AByThemeAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.SlideItemAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment.ShareElementActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;

public class AnimationActivty extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("overridePendingTransition", AAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过设置Theme", AByThemeAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过设置ActivityOption", AActivityOptionsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Fragment共享元素", ShareElementActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity共享元素", AShareModuleActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("下滑退出", RecyclerViewEventActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Transition的使用", TransitionActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("列表Item滑动带着Fragment滑出", SlideItemAnimationActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
