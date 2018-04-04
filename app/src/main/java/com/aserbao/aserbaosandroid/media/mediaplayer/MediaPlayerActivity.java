package com.aserbao.aserbaosandroid.media.mediaplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPlayerActivity extends AppCompatActivity {

    @BindView(R.id.media_surface_view)
    SurfaceView mMediaSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        ButterKnife.bind(this);
    }
}
