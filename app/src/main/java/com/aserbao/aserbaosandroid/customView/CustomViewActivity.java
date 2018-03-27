package com.aserbao.aserbaosandroid.customView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.customView.countdownView.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends AppCompatActivity {

    @BindView(R.id.custom_tainer)
    FrameLayout mCustomTainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
    }

    public void btn_count_down_btn(View view) {
        CountDownView countDownView = new CountDownView(this);
        mCustomTainer.addView(countDownView);
        countDownView.invalidate();
    }
}
