package com.aserbao.aserbaosandroid.AudioAndVideo.media.jCVideoPlayer.play;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.MyJZVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

public class JCPlayerActivity extends AppCompatActivity {

    @BindView(R.id.jz_video_player)
    MyJZVideoPlayer mJzVideoPlayer;



    public static void lanuch(Activity activity,String videoUrl){
        Intent intent = new Intent(activity, JCPlayerActivity.class);
        intent.putExtra(StaticFinalValues.VIDEO_URL,videoUrl);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcplayer);
        ButterKnife.bind(this);
        mJzVideoPlayer.post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    private void init() {
//        String s = "http://tv.cctv.com/live/cctv1/?date=2019-02-28&index=0";
        String stringExtra = getIntent().getStringExtra(StaticFinalValues.VIDEO_URL);
        mJzVideoPlayer.setUp(stringExtra, JZVideoPlayer.SCREEN_WINDOW_NORMAL,"");
        mJzVideoPlayer.startVideo();
    }
}
