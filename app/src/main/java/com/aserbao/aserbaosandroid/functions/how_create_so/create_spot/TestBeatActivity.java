package com.aserbao.aserbaosandroid.functions.how_create_so.create_spot;

import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

public class TestBeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_beat);
    }

    public void btn_test(View view) {
        String inputMusicPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/spot_temp";
        GetAudioDb getAudioDb = new GetAudioDb();
        getAudioDb.start(inputMusicPath, new IGetVideoDbCallBackListener() {
            @Override
            public void cuurentFrequenty(boolean isEnd, double volume, float cuurTime) {

            }
        });
    }
}
