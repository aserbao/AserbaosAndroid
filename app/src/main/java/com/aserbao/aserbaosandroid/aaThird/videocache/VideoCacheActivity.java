package com.aserbao.aserbaosandroid.aaThird.videocache;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.aserbao.aserbaosandroid.AUtils.utils.AppCleanMgr;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.MyJZVideoPlayer;
import com.aserbao.aserbaosandroid.other.compare.Video;
import com.bumptech.glide.Glide;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;

import java.io.File;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static cn.jzvd.JZVideoPlayer.SCREEN_WINDOW_NORMAL;

public class VideoCacheActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    MyJZVideoPlayer mVideoView;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.video_iv)
    ImageView mVideoIv;
    String baseUrl = "https://vdse.bdstatic.com//0afe2d814075c5bd2d54bea1c5b6731a?authorization=bce-auth-v1%2F40f207e648424f47b2e3dfbb1014b1a5%2F2017-05-11T09%3A02%3A31Z%2F-1%2F%2F040e567458cb59443b7405bfbbdd79982bc20bca590c3445b74a0dca940d4e64";
    String baseUrl2 = "http://v2.cri.cn/M00/01/B6/CqgUKFzFTeeAbkhcAIj6IeoQjfM483.mp4";
    String picUrl = "http://pic.zhutou.com/html/UploadPic/2010-6/2010664120959.jpg";
    private HttpProxyCacheServer mProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_use_media_player);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mBtn1.setText("开始缓存");
        mBtn1.setVisibility(View.VISIBLE);

        mBtn2.setText("检测缓存进度");
        mBtn2.setVisibility(View.VISIBLE);

        mProxy = AserbaoApplication.getProxy();
    }

    private static final String TAG = "VideoCacheActivity";
    @OnClick({R.id.play_btn, R.id.stop_btn, R.id.clear_btn, R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_btn:
                mVideoView.setUp(mProxy.getProxyUrl(baseUrl),SCREEN_WINDOW_NORMAL,"");
                mVideoView.startVideo();

//                Glide.with(this).load(picUrl).into(mVideoIv);
                break;
            case R.id.stop_btn:
//                mVideoView.onStatePause();
                statThread1();
                statThread2();
                break;
            case R.id.clear_btn:
                AppCleanMgr.cleanApplicationData(this);
                break;
            case R.id.btn_1:
                mProxy.registerCacheListener(new CacheListener() {
                    @Override
                    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
                        Log.e(TAG, "onCacheAvailable: " + percentsAvailable );

                        if (percentsAvailable == 100){
                            Log.e(TAG, "onCacheAvailable: url1" + mProxy.isCached(baseUrl) );
                        }
                    }
                },baseUrl);
                mProxy.registerCacheListener(new CacheListener() {
                    @Override
                    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
                        Log.e(TAG, "onCacheAvailable: url2 " + percentsAvailable );
                    }
                },baseUrl2);
               String proxyUrl = mProxy.getProxyUrl(baseUrl);
               String proxyUrl2 = mProxy.getProxyUrl(baseUrl2);
               mProxy.shutdown();

//                VideoView videoView = new VideoView(this);
//                videoView.setVideoURI(Uri.parse(proxyUrl));
//                videoView.start();

                 mVideoView.setUp(proxyUrl,SCREEN_WINDOW_NORMAL,"");
                 mVideoView.startVideo();
                Log.e(TAG, "onViewClicked: " + proxyUrl);
                break;
            case R.id.btn_2:
                boolean cached = mProxy.isCached(baseUrl);
                Toast.makeText(this, String.valueOf(cached), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onViewClicked: " + cached );
                break;
        }
    }


    private final Object clientsLock = new Object();
    int i = 0;
    public void statThread1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (clientsLock) {
                    while(i < 100) {
                        i++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG, "run: one " + i);
                    }
                }
            }
        }).start();
    }
    public void statThread2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (clientsLock) {
                    while(i < 200) {
                        i++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG, "run: two =========== " + i);
                    }
                }
            }
        }).start();
    }
}
