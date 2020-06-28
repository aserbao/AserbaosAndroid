package com.aserbao.aserbaosandroid.aaSource.android.java.util.concurent.executor;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/6/28
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.java.util.concurent.executor
 */
class NestPool {
    private static final String TAG = "NestPool";
    private int index;
     ExecutorService executorService = Executors.newFixedThreadPool(4);
    Object lock = new Object();
    AtomicBoolean muxStarted = new AtomicBoolean(false);

    public NestPool(int index) {
        this.index = index;
        Log.e(TAG, "run: 输入标记 " + index );
    }

    public void start(){
        executorService.execute(runnable1);
        executorService.execute(runnable2);
    }
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                startMuxer(1);
                int millis = new Random().nextInt(5) * 1000;
                Thread.sleep((long)millis);
                lock(1);
                Log.e(TAG, "runnable1  run: " + index + " muxStarted="+ muxStarted.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            try {
                  startMuxer(2);
                  int millis = new Random().nextInt(5) * 1000;
                  Thread.sleep((long)millis);
                  lock(2);
                  Log.e(TAG, "runnable2  run: " + index  + " muxStarted="+ muxStarted.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public void lock(int comeFrom){
        if(!muxStarted.get()){
            Log.e(TAG, "lock wait: comeFrom = " + comeFrom );
            synchronized (lock){
                try {
                    lock.wait();
                    Log.e(TAG, "lock: comeFrom = " + comeFrom );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG, "lock: v = " + e.toString() );
                }
            }
        }
    }
    int comeFrom1= -1,comeFrom2 = -1;

    public void startMuxer(int comeFrom){
        if(comeFrom == 1){
            comeFrom1 = 1;
        }else if(comeFrom == 2){
            comeFrom2 = 1;
        }
        Log.e(TAG, "startMuxer: " + comeFrom + " comeFrom1="+ comeFrom1 + " comeFrom2="+comeFrom2 );
        if (!muxStarted.get() && comeFrom1 > 0 && comeFrom2 > 0){
            synchronized (lock){
                muxStarted.set(true) ;
                lock.notify();
                Log.e(TAG, "startMuxer: lock.notify()" );
            }
        }
    }
}
