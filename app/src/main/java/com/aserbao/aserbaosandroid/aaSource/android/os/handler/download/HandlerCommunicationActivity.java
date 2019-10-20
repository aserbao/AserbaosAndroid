package com.aserbao.aserbaosandroid.aaSource.android.os.handler.download;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.MainLooper;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.extend.BaseAboutProgressActivity;

import java.lang.ref.WeakReference;

public class HandlerCommunicationActivity extends BaseAboutProgressActivity {
    private static final String TAG = "HandlerCommunicationActivity";

    private Thread mThread;

    @Override
    public void initGetData() {
        super.initGetData();
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {}

    private boolean mIsPause = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.progress_start_btn:
                if (curProgress == MAX_PROGRESS) curProgress = 0;
                mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (curProgress < MAX_PROGRESS && !mIsPause) {
                            try {
                                Thread.sleep(15);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.e(TAG, "run: " );
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
                break;
            case R.id.progress_stop_btn:
                mIsPause = true;
                break;
            case R.id.progress_reset_btn:
                curProgress = 0;
                break;
        }
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
                Log.e(TAG, "handleMessage: " + Thread.currentThread() );
                MainLooper.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainLooper.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                handlerActivity.updateProgress(msg.arg1);
                            }
                        });
                    }
                });
            }
        }
    }
}
