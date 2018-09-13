package com.aserbao.aserbaosandroid.AUtils.AUI.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import java.lang.ref.WeakReference;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/13
 * email: 1142803753@qq.com
 */
public class ACustomRecordProgress extends View {

    public static final int INT_START_RECORDER = 0;
    public static final int INT_STOP = 1;
    private Paint mPaint;
    private Bitmap mBitmap;
    private long mMaxDuration = 5 * 1000;

    private boolean mIsRecording = false;
    private long mStartTime = 0;
    private static final String TAG = "ACustomRecordProgress";

    public ACustomRecordProgress(Context context) {
        this(context,null);
    }

    public ACustomRecordProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ACustomRecordProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camerabutton);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect src = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        int imagePadding = 0;
        // 指定图片在屏幕上显示的区域
        if(mIsRecording){
            long timeInteral = System.currentTimeMillis() - mStartTime;
            float cuurAngel = ((float) timeInteral / (float) mMaxDuration) * 360;
            imagePadding = 25;
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(10);
            int r = 5;
            canvas.drawArc(r,r,getWidth() - r,getHeight() -r,-90,cuurAngel,false,mPaint);
            canvas.rotate(cuurAngel,getWidth()/2,getHeight() /2);
            Log.e(TAG, "onDraw: " + cuurAngel );
        }
        Rect dst = new Rect(imagePadding,imagePadding,getWidth() - imagePadding,getHeight() -imagePadding);
        canvas.drawBitmap(mBitmap,src,dst,mPaint);
    }


    public void setMaxDuration(long duration){
        mMaxDuration = duration;
    }

    public void startRecording(){
        mIsRecording = true;
        mStartTime = System.currentTimeMillis();
        myHandler.sendEmptyMessageDelayed(INT_START_RECORDER,10);
    }
    public void stopRecording(){
        mIsRecording = false;
        myHandler.sendEmptyMessage(INT_STOP);
    }

    private MyHandler myHandler = new MyHandler(this);
    private class MyHandler extends Handler {
        private WeakReference<ACustomRecordProgress> customRecordProgressWeakReference;

        public MyHandler(ACustomRecordProgress aCustomRecordProgress) {
            this.customRecordProgressWeakReference = new WeakReference<>(aCustomRecordProgress);
        }

        @Override
        public void handleMessage(Message msg) {
            ACustomRecordProgress aCustomRecordProgress = customRecordProgressWeakReference.get();
            if (aCustomRecordProgress != null) {
                switch (msg.what){
                    case INT_START_RECORDER:
                        long timeInter = System.currentTimeMillis() - aCustomRecordProgress.mStartTime;
                        if(timeInter <= aCustomRecordProgress.mMaxDuration){
                            sendEmptyMessageDelayed(INT_START_RECORDER,10);
                            invalidate();
                        }else{
                            removeMessages(INT_START_RECORDER);
                        }
                        break;
                    case INT_STOP:
                        removeMessages(INT_START_RECORDER);
                        removeMessages(INT_STOP);
                        break;
                }
            }
        }
    }

    public interface OnRecordStatusListener{
        void isTimeOver();//达到指定时间
    }
}
