package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.urlAnalyze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UrlAnalyzeActivity extends AppCompatActivity {

    private int VideoType;
    private int type;
    private String VideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_analyze);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.url_btn, R.id.url_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.url_btn:
                break;
            case R.id.url_btn2:
                break;
        }
    }

    private void judgeVideoType(String url) {
        if (url.indexOf("weishi") != -1) {
            //Type值=微视
            VideoType = 3;
            type = 2;
            VideoUrl = url.substring(url.indexOf("https"));
        } else if (url.indexOf("douyin") != -1) {
            //Type值=抖音
            VideoType = 4;
            String douyin_else;
            type = 0;
            douyin_else = url.substring(url.indexOf("http"));
            if (douyin_else.indexOf(" 复制") != -1) {
                VideoUrl = douyin_else.substring(0, douyin_else.indexOf(" 复制"));
            } else {
                VideoUrl = douyin_else;
            }
        } else {
            //Type值=快手
            VideoType = 2;
            type = 1;
            VideoUrl = url.substring(url.indexOf("http"));
        }
    }
    private void parsingVideo(int Type) {
        new Thread(() -> {
            try {
                JieXiConstant.getVideoJiexiUrl(getApplicationContext(), VideoUrl, Type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
