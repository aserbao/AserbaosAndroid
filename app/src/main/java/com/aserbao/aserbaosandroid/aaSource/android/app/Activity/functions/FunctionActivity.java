package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.functions;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.functions.gif.GifDecoder;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.tools.BitmapUtils;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FunctionActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "FunctionActivity";
    @Override
    public void initGetData() {
        //用这个https://gist.github.com/devunwired/4479231 库的代码解析gif
        mBaseRecyclerBean.add(new BaseRecyclerBean("Gif解析成图片",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Gif解析成图片",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                gifDecoder();
                break;
            case 1:

                break;
        }
    }


    public void gifDecoder(){
        try {
            GifDecoder mGifDecoder = new GifDecoder();
            //        InputStream inputStream  = mRes.openRawResource(absolutePath);
            String inputGifPath = "/storage/emulated/0/question.gif";
            String absolute = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp";
            InputStream inputStream = null;
            inputStream = new FileInputStream(inputGifPath);

            int contentLength = inputStream.available();
            int read = mGifDecoder.read(inputStream, contentLength);
            Log.e(TAG, "gifDecoder: " + contentLength  + " read ="+ read + " mGifDecoder.getFrameCount() = " + mGifDecoder.getFrameCount());

            for (int i = 0; i < mGifDecoder.getFrameCount(); i++) {
                mGifDecoder.advance();
                Bitmap nextFrame = mGifDecoder.getNextFrame();
                String path = absolute + "/" + i + ".png";
                BitmapUtils.saveBitmap2File(nextFrame,path);
            }

            Log.e(TAG, "gifDecoder: 完成"  );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
