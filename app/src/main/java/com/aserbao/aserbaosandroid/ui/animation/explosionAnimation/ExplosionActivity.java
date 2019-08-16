package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.LeonidsActivity;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.ViewExplosionActivity;

public class ExplosionActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("视图爆炸动效", ViewExplosionActivity.class));
        mClassBeen.add(new ClassBean("烟花爆炸动效", LeonidsActivity.class));
    }
}
