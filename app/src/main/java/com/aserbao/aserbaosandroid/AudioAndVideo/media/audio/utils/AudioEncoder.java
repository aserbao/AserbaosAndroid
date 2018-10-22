package com.aserbao.aserbaosandroid.AudioAndVideo.media.audio.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaRecorder;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AudioEncoder {
    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO; //音频通道(单声道)
    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT; //音频格式
    private static final int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC; //音频源（麦克风）
    private static final String AUDIO_MIME_TYPE = "audio/mp4a-latm"; //音频类型
    private static final int SAMPLE_RATE = 44100; //采样率(CD音质)
    private static final int BIT_RATE = 96000; //比特率
    private static final int CHANNEL_COUNT = 2;//声道


    private MediaCodec mAudioCodec; //音频编解码器
    private MediaMuxer mMediaMuxer;

    //初始化
    public void initAudio() {
        String path = "/storage/emulated/0/ych/" + System.currentTimeMillis() + ".mp4";
        MediaFormat mAudioFormat = new MediaFormat();
        mAudioFormat.setString(MediaFormat.KEY_MIME, AUDIO_MIME_TYPE);
        mAudioFormat.setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC);
        mAudioFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, SAMPLE_RATE);
        mAudioFormat.setInteger(MediaFormat.KEY_BIT_RATE, BIT_RATE);
        mAudioFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, CHANNEL_COUNT);
        try {
            mAudioCodec = MediaCodec.createEncoderByType(AUDIO_MIME_TYPE);
            mAudioCodec.configure(mAudioFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            mAudioCodec.start();
            mMediaMuxer = new MediaMuxer(path, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long audioStartTime;
    //开始
    public void start() {
        //麦克风采集线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                int bufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);
                AudioRecord audioRecord = new AudioRecord(
                        AUDIO_SOURCE,//音频源
                        SAMPLE_RATE,//采样率
                        CHANNEL_CONFIG,//音频通道
                        AUDIO_FORMAT,//音频格式
                        bufferSizeInBytes//缓冲区
                );
                if (audioRecord.getState() != AudioRecord.STATE_INITIALIZED) {
                    //麦克风被占用
                    return;
                }
                audioRecord.startRecording();
                byte[] buffer = new byte[bufferSizeInBytes];
                audioStartTime = System.nanoTime();
                while (stop) {
                    long audioPresentationTimeNs = System.nanoTime();
                    int bufferReadResult = audioRecord.read(buffer, 0, bufferSizeInBytes);
                    if (bufferReadResult == AudioRecord.ERROR_BAD_VALUE || bufferReadResult == AudioRecord.ERROR_INVALID_OPERATION) {

                    }
                    //向MediaCodec传数据
                    audioInputBuffer(buffer, audioPresentationTimeNs, false);
                }
                long audioPresentationTimeNs = System.nanoTime();
                int bufferReadResult = audioRecord.read(buffer, 0, bufferSizeInBytes);
                if (bufferReadResult == AudioRecord.ERROR_BAD_VALUE || bufferReadResult == AudioRecord.ERROR_INVALID_OPERATION) {

                }
                audioInputBuffer(buffer, audioPresentationTimeNs, true);
                audioRecord.stop();
                audioRecord.release();
            }
        };
        //编码线程
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                offerAudioEncoder();
            }
        };
        //相互独立的线程
        t.start();
        thread.start();
    }

    public void audioInputBuffer(byte[] buffer, long pts, boolean audioEnd) {
        int inputBufferIndex = mAudioCodec.dequeueInputBuffer(-1);
        if (inputBufferIndex >= 0) {
            ByteBuffer inputBuffer = mAudioCodec.getInputBuffer(inputBufferIndex);
            inputBuffer.clear();
            inputBuffer.put(buffer);
            long presentationTimeUs = (pts - audioStartTime) / 1000;
            //MediaCodec接收数据
            if (audioEnd) {
                mAudioCodec.queueInputBuffer(inputBufferIndex, 0, buffer.length, presentationTimeUs, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
            } else {
                mAudioCodec.queueInputBuffer(inputBufferIndex, 0, buffer.length, presentationTimeUs, 0);
            }
        }
    }

    private void offerAudioEncoder() {
        int mTrackIndex = -1;
        MediaCodec.BufferInfo mBufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            //阻塞线程等待数据，时间100毫秒
            int encoderIndex = mAudioCodec.dequeueOutputBuffer(mBufferInfo, 100);
            if (encoderIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {

            } else if (encoderIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                //开启MediaMuxer，因为只有录音所以只调用一次
                MediaFormat mediaFormat = mAudioCodec.getOutputFormat();
                mTrackIndex = mMediaMuxer.addTrack(mediaFormat);
                mMediaMuxer.start();
            } else if (encoderIndex < 0) {

            } else {
                //有数据时进入这里取当前数据
                ByteBuffer encodedData = mAudioCodec.getOutputBuffer(encoderIndex);
                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                    mBufferInfo.size = 0;
                }
                //将数据写入mMediaMuxer，结束写入开始下次循环
                if (mBufferInfo.size != 0) {
                    encodedData.position(mBufferInfo.offset);
                    encodedData.limit(mBufferInfo.offset + mBufferInfo.size);
                    mMediaMuxer.writeSampleData(mTrackIndex, encodedData, mBufferInfo);
                    mAudioCodec.releaseOutputBuffer(encoderIndex, false);
                }
                //判断是否是关闭录音
                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                    break;      // out of while
                }
            }
        }
        //释放
        release();

    }

    public void release() {
        if (mAudioCodec != null) {
            mAudioCodec.stop();
            mAudioCodec.release();
            mAudioCodec = null;
        }
        if (mMediaMuxer != null) {
            mMediaMuxer.release();
            mMediaMuxer = null;
        }
    }

    private boolean stop = true;
    //结束
    public void stop() {
        stop = false;
    }
}