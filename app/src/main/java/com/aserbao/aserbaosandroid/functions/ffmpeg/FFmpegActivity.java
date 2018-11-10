package com.aserbao.aserbaosandroid.functions.ffmpeg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.example.aserbaoffmpeg.FFmpegUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FFmpegActivity extends AppCompatActivity {

    @BindView(R.id.ffmpeg_show_tv)
    TextView mFfmpegShowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.one_btn, R.id.two_btn, R.id.three_btn, R.id.four_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one_btn:
                mFfmpegShowTv.setText(FFmpegUtils.avcodecInfo());
                break;
            case R.id.two_btn:
                mFfmpegShowTv.setText(FFmpegUtils.avfilterInfo());
                break;
            case R.id.three_btn:
                mFfmpegShowTv.setText(FFmpegUtils.avformatInfo());
                break;
            case R.id.four_btn:
                mFfmpegShowTv.setText(FFmpegUtils.configurationInfo());
                break;
        }
    }
}
