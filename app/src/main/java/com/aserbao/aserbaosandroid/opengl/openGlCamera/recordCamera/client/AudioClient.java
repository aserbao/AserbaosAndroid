package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.client;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.audio.AudioCore;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder.MediaMuxerWrapper;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.softaudiofilter.BaseSoftAudioFilter;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.MediaMakerConfig;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.RecordConfig;

/**
 * Created by lake on 16-5-24.
 */
public class AudioClient {
    MediaMakerConfig mediaMakerConfig;
    private final Object syncOp = new Object();
    private AudioRecordThread audioRecordThread;
    private AudioRecord audioRecord;
    private byte[] audioBuffer;
    private AudioCore softAudioCore;

    public AudioClient(MediaMakerConfig parameters) {
        mediaMakerConfig = parameters;
    }

    public boolean prepare(RecordConfig recordConfig) {
        synchronized (syncOp) {
            mediaMakerConfig.audioBufferQueueNum = 5;
            softAudioCore = new AudioCore(mediaMakerConfig);
            if (!softAudioCore.prepare(recordConfig)) {
                Log.e("","AudioClient,prepare");
                return false;
            }
            mediaMakerConfig.audioRecoderFormat = AudioFormat.ENCODING_PCM_16BIT;
            mediaMakerConfig.audioRecoderChannelConfig = AudioFormat.CHANNEL_IN_MONO;
            mediaMakerConfig.audioRecoderSliceSize = mediaMakerConfig.mediacodecAACSampleRate / 10;
            mediaMakerConfig.audioRecoderBufferSize = mediaMakerConfig.audioRecoderSliceSize * 2;
            mediaMakerConfig.audioRecoderSource = MediaRecorder.AudioSource.DEFAULT;
            mediaMakerConfig.audioRecoderSampleRate = mediaMakerConfig.mediacodecAACSampleRate;
            prepareAudio();
            return true;
        }
    }

    public boolean startRecording(MediaMuxerWrapper muxer) {
        synchronized (syncOp) {
            softAudioCore.startRecording(muxer);
            audioRecord.startRecording();
            audioRecordThread = new AudioRecordThread();
            audioRecordThread.start();
            Log.d("","AudioClient,start()");
            return true;
        }
    }

    public boolean stopRecording() {
        synchronized (syncOp) {
            if (audioRecordThread != null) {
                audioRecordThread.quit();
                try {
                    audioRecordThread.join();
                } catch (InterruptedException ignored) {
                }
                audioRecordThread = null;
            }
            softAudioCore.stop();
            audioRecord.stop();
            return true;
        }
    }

    public boolean destroy() {
        synchronized (syncOp) {
            audioRecord.release();
            return true;
        }
    }
    public void setSoftAudioFilter(BaseSoftAudioFilter baseSoftAudioFilter) {
        softAudioCore.setAudioFilter(baseSoftAudioFilter);
    }
    public BaseSoftAudioFilter acquireSoftAudioFilter() {
        return softAudioCore.acquireAudioFilter();
    }

    public void releaseSoftAudioFilter() {
        softAudioCore.releaseAudioFilter();
    }

    private boolean prepareAudio() {
        int minBufferSize = AudioRecord.getMinBufferSize(mediaMakerConfig.audioRecoderSampleRate,
                mediaMakerConfig.audioRecoderChannelConfig,
                mediaMakerConfig.audioRecoderFormat);
        audioRecord = new AudioRecord(mediaMakerConfig.audioRecoderSource,
                mediaMakerConfig.audioRecoderSampleRate,
                mediaMakerConfig.audioRecoderChannelConfig,
                mediaMakerConfig.audioRecoderFormat,
                minBufferSize * 5);
        audioBuffer = new byte[mediaMakerConfig.audioRecoderBufferSize];
        if (AudioRecord.STATE_INITIALIZED != audioRecord.getState()) {
            Log.e("","audioRecord.getState()!=AudioRecord.STATE_INITIALIZED!");
            return false;
        }
        if (AudioRecord.SUCCESS != audioRecord.setPositionNotificationPeriod(mediaMakerConfig.audioRecoderSliceSize)) {
            Log.e("","AudioRecord.SUCCESS != audioRecord.setPositionNotificationPeriod(" + mediaMakerConfig.audioRecoderSliceSize + ")");
            return false;
        }
        return true;
    }

    class AudioRecordThread extends Thread {
        private boolean isRunning = true;

        AudioRecordThread() {
            isRunning = true;
        }

        public void quit() {
            isRunning = false;
        }

        @Override
        public void run() {
            Log.d("","AudioRecordThread,tid=" + Thread.currentThread().getId());
            while (isRunning) {
                int size = audioRecord.read(audioBuffer, 0, audioBuffer.length);
                if (isRunning && softAudioCore != null && size > 0) {
                    softAudioCore.queueAudio(audioBuffer);
                }
            }
        }
    }
}
