package com.aserbao.aserbaosandroid.ui.customView.countdownView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountDownViewActivity extends AppCompatActivity {
//2018-03-28 11:09:16
    @BindView(R.id.custom_tainer)
    FrameLayout mCustomTainer;
    @BindView(R.id.custom_count_down_view)
    CountDownView mCountDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_view);
        ButterKnife.bind(this);
    }

    public void btn_count_down_btn(View view) {
        mCountDownView.setTimes("2018-03-29 14:15:30");
    }
}
