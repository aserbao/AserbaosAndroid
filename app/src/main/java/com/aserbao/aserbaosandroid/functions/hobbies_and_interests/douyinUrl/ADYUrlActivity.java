package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.MyJZVideoPlayer;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.beans.Video;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.interfaces.IAnalyzeTaskCallBack;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.utils.AnalyzerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;

/**
 * 抖音下载
 */
public class ADYUrlActivity extends AppCompatActivity implements IAnalyzeTaskCallBack {

    @BindView(R.id.dou_yin_jz_video_player)
    MyJZVideoPlayer mDouYinJzVideoPlayer;
    private IAnalyzeTaskCallBack mIAnalyzeTaskCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dou_yin_url);
        ButterKnife.bind(this);
        mIAnalyzeTaskCallBack = this;
    }


    @OnClick({R.id.btn_start, R.id.btn_start_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startAnalyze();
                break;
            case R.id.btn_start_down:

                break;
        }
    }

    public void startAnalyze() {
        AnalyzerTask analyzerTask = new AnalyzerTask(this, mIAnalyzeTaskCallBack);
        analyzerTask.execute("http://v.douyin.com/8vtyD3/");
    }


    @Override
    public void onAnalzed(Video video) {
        mDouYinJzVideoPlayer.setUp(video.getUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL,"");
        mDouYinJzVideoPlayer.startVideo();
    }

    @Override
    public void onCanceled() {

    }

    @Override
    public void onAnalyzeError(Exception e) {

    }
}
