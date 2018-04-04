package com.aserbao.aserbaosandroid.media.audio;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecordActivity extends AppCompatActivity {

    public AudioRecordManager mInstance;
    private int status = 0;
    public String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        ButterKnife.bind(this);
        initAudioRecord();
    }

    private void initAudioRecord() {
        mInstance = AudioRecordManager.getInstance();
    }

    @OnClick({R.id.btn_record, R.id.btn_tanslter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_record:
                if (status == 0) {
                    mPath = Environment.getExternalStorageDirectory() + "/Test";
                    if (!new File(mPath).exists()) {
                        File file = new File(mPath);
                        file.mkdir();
                    }
                    mInstance.startRecord(mPath + "/test.pcm");
                } else {
                    mInstance.stopRecord();
                }
                status ^= 1;
                break;
            case R.id.btn_tanslter:
                mInstance.playAudio();
                break;
        }
    }
}
