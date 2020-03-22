package com.aserbao.aserbaosandroid.aaSource.android.support.constraint;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;

/**
 * question1: 为什么constraintSet.centerVertically在Activity中可以生效，而在fragment中不能生效？
 */
public class ConstraintLayoutActvity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test);
        ButterKnife.bind(this);
    }
}
