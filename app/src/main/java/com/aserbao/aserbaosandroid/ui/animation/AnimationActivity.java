package com.aserbao.aserbaosandroid.ui.animation;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.animation.cubeAnimation.CubeAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.ExplosionActivity;
import com.aserbao.aserbaosandroid.ui.animation.fragments3DAnimation.Fragment3DAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.lottie.LottieActivity;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.MoveAnimActivity;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.RecyclerViewItemAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.reside.ResideActivity;
import com.aserbao.aserbaosandroid.ui.animation.scalpeAnimation.ScalpeAniamtionActivity;

public class AnimationActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Fragment的3D切换效果", Fragment3DAnimationActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView的3D切换效果", RecyclerViewItemAnimationActivity.class));
        mClassBeen.add(new ClassBean("CubeAnimation效果", CubeAnimationActivity.class));
        mClassBeen.add(new ClassBean("Lottie的效果", LottieActivity.class));
        mClassBeen.add(new ClassBean("爆炸效果", ExplosionActivity.class));
        mClassBeen.add(new ClassBean("射击动画", MoveAnimActivity.class));
        mClassBeen.add(new ClassBean("布局检测", ScalpeAniamtionActivity.class));
        mClassBeen.add(new ClassBean("Reside", ResideActivity.class));
    }
}
