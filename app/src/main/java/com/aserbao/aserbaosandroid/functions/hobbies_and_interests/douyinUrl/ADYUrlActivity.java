package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

    String path = "https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc56b1202b356a5c26cc16c58721b586db60a10d4039778a35f1ac1adfffd0dfa9077619838d88756d585c47cb2d85fa1cb1&line=0";
    @OnClick({R.id.btn_start, R.id.btn_start_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startAnalyze();
                path = "http://v9-dy.ixigua.com/1d4f43e72a0fb3200b4f0399e3b0ee23/5d49b094/video/m/220bf9b2ed593b742c193969526a05b66fe1162ec338000039835bf34386/?rc=M3A3Znc8b3hsbjMzOWkzM0ApcHpAb0k5NDw1NTk0NDY2Njk7PDNAKTZoZ2hkZ2llPDc1ODs1ODlnKXUpQGczdSlAZjN1KTU0ZF9hLTFnZGI0a18tLTQtL3NzYmJebyMwMi0tLS0yLS0uMi8tLS4vaWI0Xy9hLmFhNDJfNV80NTQ6YzpiMHAjOmEtcCM6YDUuOg%3D%3D";
                break;
            case R.id.btn_start_down:
                mDouYinJzVideoPlayer.setUp(path, JZVideoPlayer.SCREEN_WINDOW_NORMAL,"");
//        mDouYinJzVideoPlayer.setUp(video.getUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL,"");
                mDouYinJzVideoPlayer.startVideo();
                break;
        }
    }

    public void startAnalyze() {
        AnalyzerTask analyzerTask = new AnalyzerTask(this, mIAnalyzeTaskCallBack);
        analyzerTask.execute("http://v.douyin.com/DkU4y9/");
    }


    @Override
    public void onAnalzed(Video video) {

    }

    @Override
    public void onCanceled() {

    }

    @Override
    public void onAnalyzeError(Exception e) {

    }
}
