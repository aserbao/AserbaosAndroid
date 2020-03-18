package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;

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
       /* new ParticleSystem(this, 10, R.drawable.star, 1000)
                .setSpeedByComponentsRange(-0.1f, 0.1f, -0.1f, 0.02f)
//		.setAcceleration(0.000003f, 90)
                .setAcceleration(0.0003f, 90) //重力加速度
                .setInitialRotationRange(0, 360)
                .setRotationSpeed(320)
                .setFadeOut(500)
                .addModifier(new ScaleModifier(0f, 1.2f, 0, 1500))
                .oneShot(mBombBtn, 10);*/

        ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.star_pink, 500);
        ps.setScaleRange(0.f, 1.3f);
        ps.setSpeedRange(0.05f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(100, new AccelerateInterpolator());
        ps.oneShot(mBombBtn, 100);

        /*ParticleSystem ps2 = new ParticleSystem(this, 100, R.drawable.star_white, 800);
        ps2.setScaleRange(0.7f, 1.3f);
        ps2.setSpeedRange(0.1f, 0.25f);
        ps.setRotationSpeedRange(90, 180);
        ps2.setFadeOut(200, new AccelerateInterpolator());
        ps2.oneShot(mBombBtn, 70);*/
    }
}
