package com.aserbao.aserbaosandroid.audioAndVideo.media.videoView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoViewActivity extends AppCompatActivity {
    private static final String TAG = "VideoViewActivity";
    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.video_seek_bar)
    SeekBar mVideoSeekBar;
    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private String videoUrl1 = "/storage/emulated/0/DCIM/Camera/VID20180319111516.mp4";
    private String videoUrl2 = "/storage/emulated/0/DCIM/Camera/VID_20190421_154730.mp4";
    private String videoUrl3 = "/storage/emulated/0/DCIM/Camera/VID_20190421_154730.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_play)
    public void onViewClicked() {
        mVideoView.setVideoPath(videoUrl3);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(TAG, "onPrepared: ");
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e(TAG, "onCompletion: ");
            }
        });
        mVideoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progress1 = seekBar.getProgress();
                mVideoView.seekTo(mVideoView.getDuration() * progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
