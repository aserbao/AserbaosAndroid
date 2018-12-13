package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.ExplosionField;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.BooleanFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.ExplodeParticleFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.FallingParticleFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.FlyawayFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.InnerFallingParticleFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.VerticalAscentFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.simpleExplosion.SimpleExplosionField;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExplosionActivity extends AppCompatActivity {

    @BindView(R.id.explosion1_iv)
    ImageView mExplosion1Iv;
    @BindView(R.id.explosion2_iv)
    ImageView mExplosion2Iv;
    @BindView(R.id.explosion3_iv)
    ImageView mExplosion3Iv;
    @BindView(R.id.explosion4_iv)
    ImageView mExplosion4Iv;
    @BindView(R.id.explosion5_iv)
    ImageView mExplosion5Iv;
    @BindView(R.id.explosion6_iv)
    ImageView mExplosion6Iv;
    @BindView(R.id.explosion7_iv)
    ImageView mExplosion7Iv;
    @BindView(R.id.explosion8_iv)
    ImageView mExplosion8Iv;
    @BindView(R.id.explosion9_iv)
    ImageView mExplosion9Iv;
    @BindView(R.id.explosion10_iv)
    TextView mExplosion10Iv;
    @BindView(R.id.explosion_root)
    FrameLayout mExplosionRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        new SimpleExplosionField(this).addListener(mExplosion1Iv);
        new SimpleExplosionField(this).addListener(mExplosion8Iv);
        new SimpleExplosionField(this).addListener(mExplosion9Iv);
        new SimpleExplosionField(this).addListener(mExplosion10Iv);
        new ExplosionField(this,new BooleanFactory()).addListener(mExplosion2Iv);
        new ExplosionField(this,new ExplodeParticleFactory()).addListener(mExplosion3Iv);
        new ExplosionField(this,new FallingParticleFactory()).addListener(mExplosion4Iv);
        new ExplosionField(this,new FlyawayFactory()).addListener(mExplosion5Iv);
        new ExplosionField(this,new InnerFallingParticleFactory()).addListener(mExplosion6Iv);
        new ExplosionField(this,new VerticalAscentFactory()).addListener(mExplosion7Iv);
    }
}
