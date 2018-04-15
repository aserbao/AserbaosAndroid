package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.audio;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder.MediaMuxerWrapper;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * Created by lakeinchina on 26/05/16.
 */
public class AudioSenderThread extends Thread {
    private static final long WAIT_TIME = 5000;//1ms;
    private MediaCodec.BufferInfo eInfo;
    private long startTime = 0;
    private MediaCodec dstAudioEncoder;

    AudioSenderThread(String name, MediaCodec encoder, MediaMuxerWrapper muxer) {
        super(name);
        eInfo = new MediaCodec.BufferInfo();
        startTime = 0;
        dstAudioEncoder = encoder;
        mWeakMuxer = new WeakReference<MediaMuxerWrapper>(muxer);
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
                    Log.e("AudioSenderThread", "failed stopping muxer", e);
                }
            }
        }
    }

    @Override
    public void run() {
        final MediaMuxerWrapper muxer = mWeakMuxer != null ? mWeakMuxer.get() : null;
        boolean isMuxerEnable = muxer != null;
        Log.w("AudioSenderThread", "muxer enable:"+isMuxerEnable);

        while (!shouldQuit) {
            int eobIndex = dstAudioEncoder.dequeueOutputBuffer(eInfo, WAIT_TIME);
            switch (eobIndex) {
                case MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED:
                    Log.d("","AudioSenderThread,MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED");
                    break;
                case MediaCodec.INFO_TRY_AGAIN_LATER:
//                        Log.d("","AudioSenderThread,MediaCodec.INFO_TRY_AGAIN_LATER");
                    break;
                case MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:
                    Log.d("","AudioSenderThread,MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:" +
                            dstAudioEncoder.getOutputFormat().toString());
                    if (isMuxerEnable) {
                        //addTrack
                        final MediaFormat format = dstAudioEncoder.getOutputFormat(); // API >= 16
                        mTrackIndex = muxer.addTrack(format);
                        muxer.start();
                        mMuxerStarted = true;
                    }
                    break;
                default:
                    Log.d("","AudioSenderThread,MediaCode,eobIndex=" + eobIndex);
                    if (startTime == 0) {
                        startTime = eInfo.presentationTimeUs / 1000;
                    }
                    /**
                     * we send audio SpecificConfig already in INFO_OUTPUT_FORMAT_CHANGED
                     * so we ignore MediaCodec.BUFFER_FLAG_CODEC_CONFIG
                     */
                    if (eInfo.flags != MediaCodec.BUFFER_FLAG_CODEC_CONFIG && eInfo.size != 0) {
                        ByteBuffer realData = dstAudioEncoder.getOutputBuffers()[eobIndex];
                        realData.position(eInfo.offset);
                        realData.limit(eInfo.offset + eInfo.size);
                        if (isMuxerEnable && mMuxerStarted) {
                            eInfo.presentationTimeUs = getPTSUs();
                            muxer.writeSampleData(mTrackIndex, realData, eInfo);
                            prevOutputPTSUs = eInfo.presentationTimeUs;
                        }
                    }
                    dstAudioEncoder.releaseOutputBuffer(eobIndex, false);
                    break;
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
