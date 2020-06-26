package com.aserbao.common.ui.video;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;


import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/8 3:59 PM
 * @email: 1142803753@qq.com
 * @project:AndroidCamera
 * @package:com.aserbao.androidcustomcamera.whole.createVideoByVoice
 * @Copyright: 个人版权所有
 */
public class EncoderVideo {
    private static final String MIME_TYPE = "video/avc";
    private static  int WIDTH = 720;
    private static  int HEIGHT = 1280;
    private static final int BIT_RATE = 4000000;
    public static final int FRAMES_PER_SECOND = 1;
    private static final int IFRAME_INTERVAL = 5;
    private static final int MEDIA_MAX_TIME = 2 * 1000; // 生成视频的最大长度
    private static final int MAX_FRAME = 3; // 生成视频的最大长度
    private Surface mInputSurface;
    public MediaMuxer mMuxer;
    private boolean mMuxerStarted;
    private int mTrackIndex;
    private long mFakePts;
    private boolean isRecording;
    private Bitmap mBitmap;

    private int cuurFrame = 0;
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaCodec mMediaCodec;
    private Paint paint = new Paint();


    public EncoderVideo(int width,int height,Bitmap bitmap) {
        WIDTH = width;
        HEIGHT = height;
        mBitmap = bitmap;
        paint.setAntiAlias(true);
    }

    private File mOutputFile;
    private void createVideo(File outputFile) throws IOException {
        cuurFrame = 0;
        prepareEncoder(outputFile);
        update(false);
    }

    private static final String TAG = "EncoderVideo";
    public void update(boolean isEnd){
        if (cuurFrame < MAX_FRAME){
             drainEncoder(false);
             generateFrame();
             cuurFrame++;
        }else{
            stopRecording();
        }
    }
    public void stopRecording() {
        isRecording = false;
        drainEncoder(true);
        releaseEncoder();
    }

    private void generateFrame() {
        Canvas canvas = mInputSurface.lockCanvas(null);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        try {
            Rect srcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
            Rect decRect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(mBitmap,srcRect,decRect,paint);
        } finally {
            mInputSurface.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * 准备视频编码器，muxer，和一个输入表面。
     */
    private void prepareEncoder(File outputFile) throws IOException {
        mBufferInfo = new MediaCodec.BufferInfo();
        MediaFormat format = MediaFormat.createVideoFormat(MIME_TYPE, WIDTH, HEIGHT);
        //1. 设置一些属性。没有指定其中的一些可能会导致MediaCodec.configure()调用抛出一个无用的异常。
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        format.setInteger(MediaFormat.KEY_BIT_RATE, BIT_RATE);//比特率(比特率越高，音视频质量越高，编码文件越大)
        format.setInteger(MediaFormat.KEY_FRAME_RATE, FRAMES_PER_SECOND);//设置帧速
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);//设置关键帧间隔时间

        //2.创建一个MediaCodec编码器，并配置格式。获取一个我们可以用于输入的表面，并将其封装到处理EGL工作的类中。
        mMediaCodec = MediaCodec.createEncoderByType(MIME_TYPE);
        mMediaCodec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        mInputSurface = mMediaCodec.createInputSurface();
        mMediaCodec.start();
        //3. 创建一个MediaMuxer。我们不能在这里添加视频跟踪和开始合成，因为我们的MediaFormat里面没有缓冲数据。
        // 只有在编码器开始处理数据后才能从编码器获得这些数据。我们实际上对多路复用音频没有兴趣。我们只是想要
        // 将从MediaCodec获得的原始H.264基本流转换为.mp4文件。
        mMuxer = new MediaMuxer(outputFile.toString(), MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);

        mMuxerStarted = false;
        mTrackIndex = -1;
    }

    private void drainEncoder(boolean endOfStream) {
        final int TIMEOUT_USEC = 100;
        if (endOfStream) {
            mMediaCodec.signalEndOfInputStream();//在输入信号end-of-stream。相当于提交一个空缓冲区。视频编码完结
        }
        ByteBuffer[] encoderOutputBuffers = mMediaCodec.getOutputBuffers();
        while (true) {
            int outputBufferIndex = mMediaCodec.dequeueOutputBuffer(mBufferInfo, TIMEOUT_USEC);
            if (outputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {//没有可以输出的数据使用时
                if (!endOfStream) {
                    break;      // out of while
                }
            } else if (outputBufferIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                //输出缓冲区已经更改，客户端必须引用新的
                encoderOutputBuffers = mMediaCodec.getOutputBuffers();
            } else if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                //输出格式发生了变化，后续数据将使用新的数据格式。
                if (mMuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                MediaFormat newFormat = mMediaCodec.getOutputFormat();
                mTrackIndex = mMuxer.addTrack(newFormat);
                mMuxer.start();
                mMuxerStarted = true;
            } else if (outputBufferIndex < 0) {
            } else {
                ByteBuffer encodedData = encoderOutputBuffers[outputBufferIndex];
                if (encodedData == null) {
                    throw new RuntimeException("encoderOutputBuffer " + outputBufferIndex +
                            " was null");
                }
                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                    //当我们得到的时候，编解码器的配置数据被拉出来，并给了muxer。这时候可以忽略。
                    mBufferInfo.size = 0;
                }
                if (mBufferInfo.size != 0) {
                    if (!mMuxerStarted) {
                        throw new RuntimeException("muxer hasn't started");
                    }
                    //调整ByteBuffer值以匹配BufferInfo。
                    encodedData.position(mBufferInfo.offset);
                    encodedData.limit(mBufferInfo.offset + mBufferInfo.size);
                    mBufferInfo.presentationTimeUs = mFakePts;
                    mFakePts += 1000000L / FRAMES_PER_SECOND;

                    mMuxer.writeSampleData(mTrackIndex, encodedData, mBufferInfo);
                }
                mMediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {

                    isRecording = false;
                    break;
                }
            }
        }
    }


    private void releaseEncoder() {
        if (mMediaCodec != null) {
            mMediaCodec.stop();
            mMediaCodec.release();
            mMediaCodec = null;
        }
        if (mInputSurface != null) {
            mInputSurface.release();
            mInputSurface = null;
        }
        if (mMuxer != null) {
            mMuxer.stop();
            mMuxer.release();
            mMuxer = null;
        }
    }
}
