package com.aserbao.aserbaosandroid.aaSource.android.media.mediaRecorder;

import android.Manifest;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.MediaCodecDecode;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.io.IOException;

public class MediaRecorderActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "MediaRecorderActivity";



    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("MediaRecorder录制音频",MediaRecorderAudioActivity.class,0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("MediaRecorder录制视频",MediaRecorderVideoActivity.class,1));
        // 请求权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new  String[]{Manifest.permission.CHANGE_CONFIGURATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},0);
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) { }



}
