package com.aserbao.aserbaosandroid.audioAndVideo.media.mediacodec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.audioAndVideo.media.mediacodec.mcRecordVideo.MCRecordVideoActivity;

public class MediaCodecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_codec);
    }

    public void btn_mc_record_video(View view) {
        startActivity(new Intent(this, MCRecordVideoActivity.class));
    }
}
