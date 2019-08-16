package com.aserbao.aserbaosandroid.audioAndVideo.media.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecordManager2 {
    public static final String TAG = "AudioRecordManager";
    private AudioRecord mRecorder;
    private DataOutputStream dos;
    private Thread recordThread;
    private boolean isStart = false;
    private boolean isPlaying = false;
    private static AudioRecordManager2 mInstance;
    private  int bufferSize;
    private int sampleRateInHz = 11025;
//    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private File recordingFile;

    public AudioRecordManager2() {
        bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, audioFormat, bufferSize * 2);
    }

    /**
     * 获取单例引用
     * 
     * @return
     */
    public static AudioRecordManager2 getInstance() {
        if (mInstance == null) {
            synchronized (AudioRecordManager2.class) {
                if (mInstance == null) {
                    mInstance = new AudioRecordManager2();
                }
            }
        }
        return mInstance;
    }

    /**
     * 销毁线程方法
     */
    private void destroyThread() {
        try {
            isStart = false;
            if (null != recordThread && Thread.State.RUNNABLE == recordThread.getState()) {
                try {
                    Thread.sleep(500);
                    recordThread.interrupt();
                } catch (Exception e) {
                    recordThread = null;
                }
            }
            recordThread = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            recordThread = null;
        }
    }

    /**
     * 启动录音线程
     */
    private void startThread() {
        destroyThread();
        isStart = true;
        if (recordThread == null) {
            recordThread = new Thread(recordRunnable);
            recordThread.start();
        }
    }

    /**
     * 录音线程
     */
    Runnable recordRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
                int bytesRecord;
                //int bufferSize = 320;
                byte[] tempBuffer = new byte[bufferSize];
                if (mRecorder.getState() != AudioRecord.STATE_INITIALIZED) {
                    stopRecord();
                    return;
                }
                mRecorder.startRecording();
                //writeToFileHead();
                while (isStart) {
                    if (null != mRecorder) {
                        bytesRecord = mRecorder.read(tempBuffer, 0, bufferSize);
                        if (bytesRecord == AudioRecord.ERROR_INVALID_OPERATION || bytesRecord == AudioRecord.ERROR_BAD_VALUE) {
                            continue;
                        }
                        if (bytesRecord != 0 && bytesRecord != -1) {
                            //在此可以对录制音频的数据进行二次处理 比如变声，压缩，降噪，增益等操作
                            //我们这里直接将pcm音频原数据写入文件 这里可以直接发送至服务器 对方采用AudioTrack进行播放原数据
                            dos.write(tempBuffer, 0, bytesRecord);
                        } else {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    /**
     * 保存文件
     * 
     * @param path
     * @throws Exception
     */
    private void setPath(String path) throws Exception {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        recordingFile = file;
        dos = new DataOutputStream(new FileOutputStream(file, true));
    }

    /**
     * 启动录音
     * 
     * @param path
     */
    public void startRecord(String path) {
        try {
            setPath(path);
            startThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止录音
     */
    public void stopRecord() {
        try {
        destroyThread();
            if (mRecorder != null) {
                if (mRecorder.getState() == AudioRecord.STATE_INITIALIZED) {
                    mRecorder.stop();
                }
                if (mRecorder != null) {
                    mRecorder.release();
                }
            }
        if (dos != null) {
            dos.flush();
            dos.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playAudio(){
        stopRecord();
        new PlayAudio().execute();
    }
    /**
     * 播放录音
     */
    public class PlayAudio extends AsyncTask<Void,Integer,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            isPlaying = true;
            int bufferSize = AudioTrack.getMinBufferSize(sampleRateInHz,channelConfig,audioFormat);
            short[] buffer = new short[bufferSize / 4];
            DataInputStream dis= null;
            try {
                dis  = new DataInputStream(new BufferedInputStream(new FileInputStream(recordingFile)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz,channelConfig,audioFormat,bufferSize,AudioTrack.MODE_STREAM);
            audioTrack.play();
            try {
                while(isPlaying && dis.available() > 0 ){
                    int i = 0;
                    while(dis.available() > 0 && i < buffer.length){
                        buffer[i] = dis.readShort();
                        i++;
                    }
                    audioTrack.write(buffer,0,buffer.length);
                }
                dis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}