package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.LeonidsActivity;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.ViewExplosionActivity;

public class ExplosionActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("视图爆炸动效", ViewExplosionActivity.class));
        mClassBeen.add(new ClassBean("烟花爆炸动效", LeonidsActivity.class));
    }
}
