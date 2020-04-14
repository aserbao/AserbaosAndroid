package com.aserbao.common.ui.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import com.example.base.utils.screen.DisplayUtil;

import java.lang.ref.WeakReference;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/13
 * email: 1142803753@qq.com
 */
public class ARecordView extends View {

    public static final int INT_START_RECORDER = 0;
    public static final int INT_STOP = 1;
    private Paint mPaint;
    private long mMaxDuration = 30 * 1000;

    private boolean mIsRecording = false;
    private long mStartTime = 0;

    public ARecordView(Context context) {
        this(context,null);
    }

    public ARecordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ARecordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(2, 0, 0, Color.parseColor("#7F000000"));
    }

    public void setBitmap(Bitmap bitmap){
        /*isPrivate = true; //不需要头像了，用空心的
        mBitmap = bitmap;
        invalidate();*/
    }

    int mWidth,mHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
    int centerSizeWH = DisplayUtil.dip2px(20);
    int centerRadius = DisplayUtil.dip2px(2);
    int recordingColor = Color.parseColor("#D7F660");
    int recordingRadius = 24;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth/2,mHeight/2,recordingRadius,mPaint);
        if(mIsRecording){
            mPaint.setColor(Color.parseColor("#1A000000"));
            canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2- recordingRadius,mPaint);

            mPaint.setColor(recordingColor);
            int left = (mWidth - centerSizeWH) / 2;
            int top = (mHeight - centerSizeWH) / 2;
            RectF centerRectF = new RectF(left, top, left + centerSizeWH, top + centerSizeWH);
            canvas.drawRoundRect(centerRectF,centerRadius,centerRadius,mPaint);

            mPaint.setStyle(Paint.Style.STROKE);
            long timeInteral = System.currentTimeMillis() - mStartTime;
            float cuurAngel = ((float) timeInteral / (float) mMaxDuration) * 360;
            mPaint.setStrokeWidth(20);
            int r = 24;
            RectF rectF = new RectF(r, r, getWidth() - r, getHeight() - r);
            mPaint.setColor(Color.WHITE);
            canvas.drawArc(rectF,0,360,false,mPaint);
            mPaint.setColor(recordingColor);
            canvas.drawArc(rectF,-90,cuurAngel,false,mPaint);

//            canvas.rotate(cuurAngel,getWidth()/2,getHeight() /2);
        }
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
        private WeakReference<ARecordView> customRecordProgressWeakReference;

        public MyHandler(ARecordView aCustomRecordProgress) {
            this.customRecordProgressWeakReference = new WeakReference<>(aCustomRecordProgress);
        }

        @Override
        public void handleMessage(Message msg) {
            ARecordView aCustomRecordProgress = customRecordProgressWeakReference.get();
            if (aCustomRecordProgress != null) {
                switch (msg.what){
                    case INT_START_RECORDER:
                        long timeInter = System.currentTimeMillis() - aCustomRecordProgress.mStartTime;
                        if(timeInter <= aCustomRecordProgress.mMaxDuration){
                            sendEmptyMessageDelayed(INT_START_RECORDER,10);
                            invalidate();
                        }else{
                            if (mOnRecordStatusListener != null) {
                                mOnRecordStatusListener.isTimeOver();
                            }
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


    private OnRecordStatusListener mOnRecordStatusListener;
    public void setOnRecordStatusListener(OnRecordStatusListener onRecordStatusListener){
        mOnRecordStatusListener = onRecordStatusListener;
    }

    public interface OnRecordStatusListener{
        void isTimeOver();//达到指定时间
    }
}
