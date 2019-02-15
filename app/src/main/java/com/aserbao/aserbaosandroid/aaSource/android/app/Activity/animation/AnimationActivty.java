package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.overridePendingTransition.AAnimationActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class AnimationActivty extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("overridePendingTransition", AAnimationActivity.class));
    }
}
