package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.LeonidsActivity;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.ViewExplosionActivity;

public class ExplosionActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("视图爆炸动效", ViewExplosionActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("烟花爆炸动效", LeonidsActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
