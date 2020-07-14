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
import java.io.IOException;
import java.io.InputStream;

import pl.droidsonroids.gif.GifDrawable;

public class FunctionActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "FunctionActivity";
    @Override
    public void initGetData() {
        //用这个https://gist.github.com/devunwired/4479231 库的代码解析gif
        mBaseRecyclerBean.add(new BaseRecyclerBean("Gif解析成图片",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Gif解析成图片",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Gif解析成图片",2));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                gifDecoder();
                break;
            case 1:
                gifDrawableDecoder();
                break;
            case 2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        gifDrawableDecoder1();
                    }
                }).start();
                break;
        }
    }
    String inputGifPath = "/storage/emulated/0/question.gif";
    String inputGifPath1 = "/storage/emulated/0/question1.gif";
    String absolute = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp";
    public void gifDecoder(){
        try {
            GifDecoder mGifDecoder = new GifDecoder();
            //        InputStream inputStream  = mRes.openRawResource(absolutePath);

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

    public void gifDrawableDecoder(){
        try {
            GifDrawable gifFromFile = new GifDrawable(inputGifPath);
            int numberOfFrames = gifFromFile.getNumberOfFrames();
            Log.e(TAG, "gifDecoder:  GifDrawable.getFrameCount() = " + numberOfFrames);
            for (int i = 0; i < numberOfFrames; i++) {
                gifFromFile.seekToFrame(i);
                Bitmap currentFrame = gifFromFile.getCurrentFrame();
                String path = absolute + "/" + i + ".png";
                BitmapUtils.saveBitmap2File(currentFrame,path);
            }
            Log.e(TAG, "gifDecoder: 完成"  );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gifDrawableDecoder1(){
        try {
            GifDrawable gifDrawable = new GifDrawable(inputGifPath1);
            int numberOfFrames = gifDrawable.getNumberOfFrames();
            Log.e(TAG, "gifDecoder:  GifDrawable.getFrameCount() = " + numberOfFrames);
            for (int i = 0; i < numberOfFrames; i++) {
                gifDrawable.seekToFrame(i);
                Bitmap currentFrame = gifDrawable.getCurrentFrame();
                String path = absolute + "/" + i + ".png";
                BitmapUtils.saveBitmap2File(currentFrame,path);
            }
            Log.e(TAG, "gifDecoder: 完成"  );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
