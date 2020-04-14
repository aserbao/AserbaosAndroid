package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;

import com.example.base.utils.screen.DisplayUtil;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 功能:解码本地视频到Surface上显示
 *
 * @author aserbao
 * @date : On 2019-12-12 11:21
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo
 */
public class MediaCodecDecode {
    String inputVideoPath;//视频地址
    Surface mSurface; //用来显示的视图
    private MediaExtractor mMediaExtractor;
    private MediaCodec mMediaCodecDecodeByType;
    private boolean mIsAvailable = false;

    public MediaCodecDecode(String inputVideoPath, Surface mSurface) {
        this.inputVideoPath = inputVideoPath;
        this.mSurface = mSurface;
    }

    public void start(){
        init();
        if (mIsAvailable){
            mMediaCodecDecodeByType.start();
            new Thread(new DecoderThread()).start();
        }
    }

    private void init(){
        mMediaExtractor = new MediaExtractor();
        try {
            mMediaExtractor.setDataSource(inputVideoPath);
            int trackCount = mMediaExtractor.getTrackCount();
            for (int i = 0; i < trackCount; i++) {
                MediaFormat trackFormat = mMediaExtractor.getTrackFormat(i);
                int width = DisplayUtil.dip2px(300);
                int height = DisplayUtil.dip2px(400);
                trackFormat.setInteger(MediaFormat.KEY_WIDTH,width);
                trackFormat.setInteger(MediaFormat.KEY_HEIGHT,height);
                String mime = trackFormat.getString(MediaFormat.KEY_MIME);
                if (mime.startsWith("video/")){
                    mMediaExtractor.selectTrack(i);
                    mMediaCodecDecodeByType = MediaCodec.createDecoderByType(mime);
                    mMediaCodecDecodeByType.configure(trackFormat,mSurface,null,0);
                    mIsAvailable = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class DecoderThread implements Runnable{
        @Override
        public void run() {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            long startTime = System.currentTimeMillis();
            while(mIsAvailable){
                int inputIndex = mMediaCodecDecodeByType.dequeueInputBuffer(-1);
                if (inputIndex > 0){
                    ByteBuffer inputBuffer = mMediaCodecDecodeByType.getInputBuffer(inputIndex);
                    int readSampleData = mMediaExtractor.readSampleData(inputBuffer, 0);
                    if (readSampleData > 0){
                        mMediaExtractor.advance();
                        mMediaCodecDecodeByType.queueInputBuffer(inputIndex,0,readSampleData,mMediaExtractor.getSampleTime(),0);
                    }
                }

                int outputIndex = mMediaCodecDecodeByType.dequeueOutputBuffer(bufferInfo, 100);
                if (outputIndex >= 0){
                    long sleepTime = bufferInfo.presentationTimeUs / 1000 - (System.currentTimeMillis() - startTime);
                    if (sleepTime > 0){
                        SystemClock.sleep(sleepTime);
                    }
                    mMediaCodecDecodeByType.releaseOutputBuffer(outputIndex,true);
                }
            }
            release();
        }
    }

    public void release(){
        mMediaExtractor.release();
        mMediaCodecDecodeByType.stop();
        mMediaCodecDecodeByType.release();
    }
}
