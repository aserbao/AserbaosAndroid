package com.aserbao.aserbaosandroid.aaSource.android.os.handler.download;

import android.os.Handler;
import android.os.Message;

import com.example.base.base.extend.BaseAboutProgressActivity;
import com.example.base.utils.others.MainLooper;

import java.lang.ref.WeakReference;

public class HandlerCommunicationActivity extends BaseAboutProgressActivity {
    private static final String TAG = "HandlerCommunicationActivity";

    private Thread mThread;
    private boolean mIsPause = false;

    @Override
    protected void startDownload() {
        //模拟从后台获取数据
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (curProgress < MAX_PROGRESS && !mIsPause) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    curProgress++;
                    Message message = new Message();
                    message.arg1 = curProgress;
                    mMyHandler.handleMessage(message);
                }
            }
        });
        mThread.setName("This is a download Thread");
        mIsPause = false;
        mThread.start();
    }

    @Override
    protected void pauseDownload() {
        mIsPause = true;
    }


    private MyHandler mMyHandler = new MyHandler(this);

    private static class MyHandler extends Handler{
        WeakReference<HandlerCommunicationActivity> mHandlerActivityWeakReference;

        public MyHandler(HandlerCommunicationActivity handlerActivityWeakReference) {
            mHandlerActivityWeakReference = new WeakReference<>(handlerActivityWeakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerCommunicationActivity handlerActivity = mHandlerActivityWeakReference.get();
            if (handlerActivity != null) {
                MainLooper.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handlerActivity.updateProgress(msg.arg1); // 更新UI界面
                    }
                });
            }
        }
    }
}
