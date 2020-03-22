package com.aserbao.aserbaosandroid.aaSource.android.media.mediaRecorder;

import android.Manifest;
import android.os.Build;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class MediaRecorderActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "MediaRecorderActivity";



    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("MediaRecorder录制音频",MediaRecorderAudioActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("MediaRecorder录制视频",MediaRecorderVideoActivity.class));
        // 请求权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new  String[]{Manifest.permission.CHANGE_CONFIGURATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},0);
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) { }



}
