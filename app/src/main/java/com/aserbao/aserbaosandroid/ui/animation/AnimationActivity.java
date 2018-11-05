package com.aserbao.aserbaosandroid.ui.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.animation.cubeAnimation.CubeAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.fragments3DAnimation.Fragment3DAnimationActivity;
import com.aserbao.aserbaosandroid.ui.animation.lottie.LottieActivity;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.RecyclerViewItemAnimationActivity;

public class AnimationActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Fragment的3D切换效果", Fragment3DAnimationActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView的3D切换效果", RecyclerViewItemAnimationActivity.class));
        mClassBeen.add(new ClassBean("CubeAnimation效果", CubeAnimationActivity.class));
        mClassBeen.add(new ClassBean("Lottie的效果", LottieActivity.class));
    }
}
