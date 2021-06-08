package com.aserbao.aserbaosandroid.ui.animation;

import android.view.View;

import com.aserbao.aserbaosandroid.ui.animation.viewAnimation.ViewAnimationActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.animation.cubeAnimation.CubeAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.ExplosionActivity;
import com.aserbao.aserbaosandroid.ui.animation.fragments3DAnimation.Fragment3DAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.lottie.LottieActivity;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.MoveAnimActivity;
import com.aserbao.aserbaosandroid.ui.animation.baseAnimation.objectAnimator.ObjectAnimatorActivity;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.RecyclerViewItemAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.reside.ResideActivity;
import com.aserbao.aserbaosandroid.ui.animation.scalpeAnimation.ScalpeAniamtionActivity;

public class AnimationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Fragment的3D切换效果", Fragment3DAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView的3D切换效果", RecyclerViewItemAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("CubeAnimation效果", CubeAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Lottie的效果", LottieActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("爆炸效果", ExplosionActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("射击动画", MoveAnimActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("布局检测", ScalpeAniamtionActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Reside", ResideActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("属性动画", ObjectAnimatorActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("View动画", ViewAnimationActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
