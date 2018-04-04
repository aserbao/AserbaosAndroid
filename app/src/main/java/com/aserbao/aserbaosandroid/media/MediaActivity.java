package com.aserbao.aserbaosandroid.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.media.audio.AudioRecordActivity;
import com.aserbao.aserbaosandroid.media.videoView.VideoViewActivity;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
    }

    public void btn_video_view(View view) {
        startActivity(new Intent(this, VideoViewActivity.class));
    }

    public void btn_audio_record_activity(View view) {
        startActivity(new Intent(this, AudioRecordActivity.class));
    }
}
