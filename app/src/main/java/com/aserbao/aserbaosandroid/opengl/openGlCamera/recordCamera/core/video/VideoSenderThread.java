package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder.MediaMuxerWrapper;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * Created by lakeinchina on 26/05/16.
 */
public class VideoSenderThread extends Thread {
    private static final long WAIT_TIME = 5000;
    private MediaCodec.BufferInfo eInfo;
    private long startTime = 0;
    private MediaCodec dstVideoEncoder;
    private final Object syncDstVideoEncoder = new Object();

    VideoSenderThread(String name, MediaCodec encoder, MediaMuxerWrapper muxer) {
        super(name);
        eInfo = new MediaCodec.BufferInfo();
        startTime = 0;
        dstVideoEncoder = encoder;
        mWeakMuxer = new WeakReference<MediaMuxerWrapper>(muxer);
    }

    public void updateMediaCodec(MediaCodec encoder) {
        synchronized (syncDstVideoEncoder) {
            dstVideoEncoder = encoder;
        }
    }

    private boolean shouldQuit = false;

    void quit() {
        shouldQuit = true;
        this.interrupt();
        if (mMuxerStarted) {
            final MediaMuxerWrapper muxer = mWeakMuxer != null ? mWeakMuxer.get() : null;
            if (muxer != null) {
                try {
                    muxer.stop();
                } catch (final Exception e) {
                    Log.e("VideoSenderThread", "failed stopping muxer", e);
                }
            }
        }
    }

    @Override
    public void run() {
        final MediaMuxerWrapper muxer = mWeakMuxer != null ? mWeakMuxer.get() : null;
        boolean isMuxerEnable = muxer != null;
        Log.w("VideoSenderThread", "muxer enable:"+isMuxerEnable);
        while (!shouldQuit) {
            synchronized (syncDstVideoEncoder) {
                int eobIndex = MediaCodec.INFO_TRY_AGAIN_LATER;
                try {
                    eobIndex = dstVideoEncoder.dequeueOutputBuffer(eInfo, WAIT_TIME);
                } catch (Exception ignored) {
                }
                switch (eobIndex) {
                    case MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED:
                        Log.d("","VideoSenderThread,MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED");
                        break;
                    case MediaCodec.INFO_TRY_AGAIN_LATER:
//                        Log.d("","VideoSenderThread,MediaCodec.INFO_TRY_AGAIN_LATER");
                        break;
                    case MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:
                        Log.d("","VideoSenderThread,MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:" +
                                dstVideoEncoder.getOutputFormat().toString());
                        if (isMuxerEnable) {
                            //addTrack
                            final MediaFormat format = dstVideoEncoder.getOutputFormat(); // API >= 16
                            mTrackIndex = muxer.addTrack(format);
                            muxer.start();
                            mMuxerStarted = true;
                        }
                        break;
                    default:
                        Log.d("","VideoSenderThread,MediaCode,eobIndex=" + eobIndex);
                        if (startTime == 0) {
                            startTime = eInfo.presentationTimeUs / 1000;
                        }
                        /**
                         * we send sps pps already in INFO_OUTPUT_FORMAT_CHANGED
                         * so we ignore MediaCodec.BUFFER_FLAG_CODEC_CONFIG
                         */
                        if (eInfo.flags != MediaCodec.BUFFER_FLAG_CODEC_CONFIG && eInfo.size != 0) {
                            ByteBuffer realData = dstVideoEncoder.getOutputBuffers()[eobIndex];
                            realData.position(eInfo.offset + 4);
                            realData.limit(eInfo.offset + eInfo.size);
                            if (isMuxerEnable && mMuxerStarted) {
                                eInfo.presentationTimeUs = getPTSUs();
                                muxer.writeSampleData(mTrackIndex, realData, eInfo);
                                prevOutputPTSUs = eInfo.presentationTimeUs;
                            }
                        }
                        dstVideoEncoder.releaseOutputBuffer(eobIndex, false);
                        break;
                }
            }
            try {
                sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        eInfo = null;
    }

    protected WeakReference<MediaMuxerWrapper> mWeakMuxer;
    protected int mTrackIndex;
    protected boolean mMuxerStarted = false;
    /**
     * previous presentationTimeUs for writing
     */
    private long prevOutputPTSUs = 0;
    /**
     * get next encoding presentationTimeUs
     * @return
     */
    protected long getPTSUs() {
        long result = System.nanoTime() / 1000L;
        // presentationTimeUs should be monotonic
        // otherwise muxer fail to write
        if (result < prevOutputPTSUs)
            result = (prevOutputPTSUs - result) + result;
        return result;
    }
}