package com.aserbao.aserbaosandroid.ui.animation.cubeAnimation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;

public class CubeAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_animation);
        ButterKnife.bind(this);
    }
}
