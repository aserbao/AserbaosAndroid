package com.aserbao.aserbaosandroid.ui.buttons.switchButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;

public class SwitchButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        ButterKnife.bind(this);
    }
}
