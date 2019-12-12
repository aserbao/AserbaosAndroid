package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewParent;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaThird.pickvideo.VideoPickActivity;
import com.aserbao.aserbaosandroid.aaThird.pickvideo.beans.VideoFile;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;

import static com.aserbao.aserbaosandroid.aaThird.pickvideo.BaseActivity.IS_NEED_FOLDER_LIST;
import static com.aserbao.aserbaosandroid.aaThird.pickvideo.VideoPickActivity.IS_NEED_CAMERA;
import static com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues.MAX_NUMBER;

/**
 * MediaCodec视频解码到Surface上播放。
 */
public class DecodeShowVideoActivity extends BaseRecyclerViewActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceViewHolder;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"先选择视频，再播放视频"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选择本地视频",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("解码播放视频",1));

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                Intent intent2 = new Intent(this, VideoPickActivity.class);
                intent2.putExtra(IS_NEED_CAMERA, false);
                intent2.putExtra(MAX_NUMBER, 1);
                intent2.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent2, StaticFinalValues.COME_FROM_REQUEST_CODE_TAKE_VIDEO);
                break;
            case 1:
                View root = addLayoutToFrameLayout(R.layout.surface_view_layout, false);
                mSurfaceView = ((SurfaceView) root.findViewById(R.id.surface_view));
                mSurfaceViewHolder = mSurfaceView.getHolder();
                mSurfaceViewHolder.addCallback(this);

                break;
        }
    }

    private static final String TAG = "DecodeShowVideoActivity";
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        MediaCodecDecode mediaCodecDecode = new MediaCodecDecode(videoFileName, mSurfaceViewHolder.getSurface());
        mediaCodecDecode.start();
        Log.d(TAG, "surfaceCreated() called with: holder = [" + holder + "]");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged() called with: holder = [" + holder + "], format = [" + format + "], width = [" + width + "], height = [" + height + "]");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed() called with: holder = [" + holder + "]");
    }


    String videoFileName = "/storage/emulated/0/720aserbao5.mp4";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case StaticFinalValues.COME_FROM_REQUEST_CODE_TAKE_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(StaticFinalValues.RESULT_PICK_VIDEO);
                    for (VideoFile file : list) {
                        videoFileName = file.getPath();
                    }
                    Toast.makeText(this, "视频已选择成功\n" + videoFileName, Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }
}
