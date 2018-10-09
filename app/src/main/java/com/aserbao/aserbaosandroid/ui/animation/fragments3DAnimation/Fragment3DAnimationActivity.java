package com.aserbao.aserbaosandroid.ui.animation.fragments3DAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;

public class Fragment3DAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment3d_animation);
        ButterKnife.bind(this);
    }
}
