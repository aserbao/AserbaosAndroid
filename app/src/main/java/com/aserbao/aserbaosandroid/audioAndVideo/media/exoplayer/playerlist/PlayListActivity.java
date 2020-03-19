package com.aserbao.aserbaosandroid.audioAndVideo.media.exoplayer.playerlist;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.FileDescriptor;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayListActivity extends AppCompatActivity {
    public static String[] videoUrlList =
            {
                    "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4"
            };
    public static String[] videoUrlLiswt =
            {
                    "http://jzvd.nathen.cn/63f3f73712544394be981d9e4f56b612/69c5767bb9e54156b5b60a1b6edeb3b5-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/25a8d119cfa94b49a7a4117257d8ebd7/f733e65a22394abeab963908f3c336db-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/7512edd1ad834d40bb5b978402274b1a/9691c7f2d7b74b5e811965350a0e5772-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
            };
    public static String[] videoUrlList0 = {
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0300f970000bj8n2sds3mesa72u8tv0&line=0",
    };
    

    @BindView(R.id.player_list_player_view)
    PlayerView mPlayerListPlayerView;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    private SimpleExoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        ButterKnife.bind(this);
        initView(this);
    }

    private void initView(Context context) {
        Handler mainHandler = new Handler();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        DefaultTrackSelector.ParametersBuilder parametersBuilder = trackSelector.buildUponParameters();
        // 2. Create the player
        mPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        mPlayerListPlayerView.setPlayer(mPlayer);
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeters = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "yourApplicationName"), null);
        // This is the MediaSource representing the media to be played.
        ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(dataSourceFactory);
        MediaSource[] mediaSources = new MediaSource[videoUrlList0.length];
        try {
            AssetFileDescriptor fd = getAssets().openFd("black.mp4");
            FileDescriptor fileDescriptor = fd.getFileDescriptor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri mUri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.black);

        String videoUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/VID_20190421_154730.mp4";
        Uri parse = Uri.parse(videoUrl);

        for (int i = 0; i < videoUrlList0.length; i++) {
//            mediaSources[i] = factory.createMediaSource(Uri.parse(videoUrlList0[i]));
//            mediaSources[i] = factory.createMediaSource(mUri);
            mediaSources[i] = factory.createMediaSource(parse);
//            mediaSources[i] = buildMediaSource(parse);
        }


      /*  ConcatenatingMediaSource concatenatedSource =
                new ConcatenatingMediaSource(firstSource, secondSource);*/
// Prepare the player with the source.
        mPlayer.prepare(new ConcatenatingMediaSource(mediaSources));

        mPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
            new DefaultDataSourceFactory(this,"ua"),
            new DefaultExtractorsFactory(), null, null);
    }

    private static final String TAG = "PlayListActivity";


    @OnClick({R.id.btn_next, R.id.btn_before,R.id.btn_start,R.id.btn_pause,R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                /*Class<?>[] declaredClasses = mPlayerListPlayerView.getClass().getDeclaredClasses();
        for (int i = 0; i < declaredClasses.length; i++) {
            Log.e(TAG, "Clasees :  " + declaredClasses[i].getName());
        }
        Field[] declaredFields = mPlayerListPlayerView.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Log.e(TAG, "Fields   " + declaredFields[i].getName());
        }
        try {
            Field controller = mPlayerListPlayerView.getClass().getDeclaredField("controller");

            Class<? extends Field> controllerClass = controller.getClass();
            Class<?>[] declaredClasses1 = controllerClass.getDeclaredClasses();
            for (int i = 0; i < declaredClasses1.length; i++) {
                Log.e(TAG, "controller class: : " + declaredClasses1[i].getName().toString());
            }
            Field[] declaredFields1 = controllerClass.getDeclaredFields();
            for (int i = 0; i < declaredFields1.length; i++) {
                Log.e(TAG, "controller fileds: " + declaredFields1[i].getName().toString());
            }

            Method[] declaredMethods = controllerClass.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                Log.e(TAG, "btn_click: " + declaredMethods[i].getName().toString());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }*/


                int nextWindowIndex = mPlayer.getNextWindowIndex();
                mPlayer.seekTo(nextWindowIndex, 0);
                break;
            case R.id.btn_before:
                /*ImageView imageViewPrev = (ImageView) findViewById(R.id.exo_prev);
                imageViewPrev.performClick();*/

                ObjectAnimator rotationY = ObjectAnimator.ofFloat(mPlayerListPlayerView, "rotationY", 0, 30);
                rotationY.setDuration(1000);
                rotationY.start();
                break;
            case R.id.btn_start:
                /*ImageView imageViewPlay = (ImageView) findViewById(R.id.exo_play);
                if (imageViewPlay.getParent() instanceof LinearLayout) {
                    ((LinearLayout)(((LinearLayout) imageViewPlay.getParent()).getParent())).setVisibility(View.GONE);
                }
                imageViewPlay.performClick();*/
                ObjectAnimator animator = ObjectAnimator.ofFloat(mFrameLayout, "rotationY", 0, 30);
                animator.setDuration(1000);
                animator.start();

                break;
            case R.id.btn_pause:
                ImageView imageViewPause = (ImageView) findViewById(R.id.exo_pause);
                imageViewPause.performClick();
                break;
            case R.id.btn_add:
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(PlayListActivity.this,
                        Util.getUserAgent(PlayListActivity.this, "AserbaoApplication"), null);
                // This is the MediaSource representing the media to be played.
                ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(dataSourceFactory);
                MediaSource[] mediaSources = new MediaSource[videoUrlList0.length + videoUrlList.length];
                for (int i = 0; i < videoUrlList0.length; i++) {
                    mediaSources[i] = factory.createMediaSource(Uri.parse(videoUrlList0[i]));
                }
                for (int i = videoUrlList0.length; i < videoUrlList0.length + videoUrlList.length ; i++) {
                    mediaSources[i] = factory.createMediaSource(Uri.parse(videoUrlList[i - videoUrlList0.length]));
                }
                mPlayer.prepare(new ConcatenatingMediaSource(mediaSources));
                mPlayer.seekTo(videoUrlList0.length, 0);
                mPlayer.setPlayWhenReady(true);
                break;
        }
    }
}
