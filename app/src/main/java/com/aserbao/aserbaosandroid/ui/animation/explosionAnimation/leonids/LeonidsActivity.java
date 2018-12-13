package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.modifiers.ScaleModifier;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeonidsActivity extends AppCompatActivity {


    @BindView(R.id.bomb_btn)
    Button mBombBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leonids);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bomb_btn)
    public void onViewClicked() {
        new ParticleSystem(this, 10, R.drawable.star, 3000)
                .setSpeedByComponentsRange(-0.1f, 0.1f, -0.1f, 0.02f)
//		.setAcceleration(0.000003f, 90)
                .setAcceleration(0.0003f, 90) //重力加速度
                .setInitialRotationRange(0, 360)
                .setRotationSpeed(320)
                .setFadeOut(2000)
                .addModifier(new ScaleModifier(0f, 2.5f, 0, 1500))
                .oneShot(mBombBtn, 10);
    }
}
