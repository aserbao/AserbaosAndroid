package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosion.ExplosionField;

public class ExplosionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion);

        ExplosionField explosionField = new ExplosionField(this);
        explosionField.addListener(findViewById(R.id.explosion_root));
    }
}
