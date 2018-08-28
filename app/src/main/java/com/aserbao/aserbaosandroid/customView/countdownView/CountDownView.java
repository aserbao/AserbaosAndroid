package com.aserbao.aserbaosandroid.customView.countdownView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.DisplayUtil;
import com.aserbao.aserbaosandroid.AUtils.date.AppDateMgr;

import java.lang.ref.WeakReference;

import static com.aserbao.aserbaosandroid.commonData.StaticFinalValues.EMPTY;


/**
 * description:
 * Created by aserbao on 2018/3/27.
 */


public class CountDownView extends View {
    private static final String TAG = "CountDownView";
    private static final String DEFAULT_SUFFIX = ":";
    private String[] sTime = {"-","-","-","-","-","-"};//存放时间，时分秒
    private boolean isOverTime = false; //是否结束倒计时
    protected Paint mTimeTextPaint,mTimeBgPaint;
    private RectF mHour0BgRectF, mHour1BgRectF,mMinuter0BgRectF,mMinuter1BgRectF,mSeconds0BgRectF,mSeconds1BgRectF;
    private Context mContext;
    public MyHandler mMyHandler;
    private String mStartTime;//倒计时

    public CountDownView(Context context) {
        this(context,null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
        initTimeBgRect();
    }

    private void initPaint(){
        mTimeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimeTextPaint.setColor(Color.parseColor("#FAE000"));
        mTimeTextPaint.setTextAlign(Paint.Align.CENTER);
        mTimeTextPaint.setTextSize(DisplayUtil.sp2px(mContext,15));
        mTimeTextPaint.setFakeBoldText(true);


        mTimeBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimeBgPaint.setStyle(Paint.Style.FILL);
        mTimeBgPaint.setColor(Color.parseColor("#F64646"));

    }


    public void setTimes(String time){
        mStartTime = time;
        if (mMyHandler == null) {
            mMyHandler = new MyHandler(this);
        }
        mMyHandler.sendEmptyMessage(EMPTY);
    }

    int width = 80;//每个item的宽高
    int intervalWidth = 10;//点边距
    int nullWidth = 5;//两个空item的距离
    int textX = 40; //文字X轴位置
    int textY = 50; //文字y轴位置
    int radius  = 10;//圆角半径

    private void initTimeBgRect() {
        int left = 0;
        int right = width;
        if(isOverTime){
            left = (2 * width + nullWidth + 2 * intervalWidth)/2;
            right = (2 * width + nullWidth + 2 * intervalWidth)/2 + width;
        }
        mHour0BgRectF = new RectF(left,0,right,width);
        left = right + nullWidth;
        right = left + width;
        mHour1BgRectF = new RectF(left,0,right,width);
        left = right + 2 * intervalWidth;
        right = left + width;
        mMinuter0BgRectF = new RectF(left,0,right,width);
        left = right + nullWidth;
        right = left + width;
        mMinuter1BgRectF = new RectF(left,0,right,width);
        left = right + 2 * intervalWidth;
        right = left + width;
        mSeconds0BgRectF = new RectF(left,0,right,width);
        left = right + nullWidth;
        right = left + width;
        mSeconds1BgRectF = new RectF(left,0,right,width);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int x = textX;
        int y = textY;
        if(isOverTime){
            x = textX + (2 * width + nullWidth + 2 * intervalWidth)/2;
        }
        canvas.drawRoundRect(mHour0BgRectF,radius,radius,mTimeBgPaint);
        canvas.drawText(sTime[0],x,y,mTimeTextPaint);
        x = x + width + nullWidth;
        canvas.drawRoundRect(mHour1BgRectF,radius,radius,mTimeBgPaint);
        canvas.drawText(sTime[1],x,y,mTimeTextPaint);
        x = (int)mHour1BgRectF.right + intervalWidth;
        canvas.drawText(DEFAULT_SUFFIX,x,y ,mTimeTextPaint);
        //绘制分
        x = x + textX + intervalWidth;
        canvas.drawRoundRect(mMinuter0BgRectF,radius,radius,mTimeBgPaint);
        canvas.drawText(sTime[2],x,y,mTimeTextPaint);
        x = x + width + nullWidth;
        canvas.drawRoundRect(mMinuter1BgRectF,radius,radius,mTimeBgPaint);
        canvas.drawText(sTime[3],x,y,mTimeTextPaint);
        if(!isOverTime) {
            x = (int)mMinuter1BgRectF.right + intervalWidth;
            canvas.drawText(DEFAULT_SUFFIX,x,y ,mTimeTextPaint);
            //绘制秒
            x = x + textX + intervalWidth;
            canvas.drawRoundRect(mSeconds0BgRectF, radius, radius, mTimeBgPaint);
            canvas.drawText(sTime[4], x, y, mTimeTextPaint);
            x = x + width + nullWidth;
            canvas.drawRoundRect(mSeconds1BgRectF, radius, radius, mTimeBgPaint);
            canvas.drawText(sTime[5], x, y, mTimeTextPaint);
        }
        super.onDraw(canvas);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = 6 * width + 3 * nullWidth + 4 * intervalWidth + getPaddingLeft() + getPaddingRight();
        int heightSize = width;
        setMeasuredDimension(widthSize, heightSize);
    }

    static class MyHandler extends Handler{
        private WeakReference<CountDownView> mCountDownViewWeakReference;

        public MyHandler(CountDownView countDownViewWeakReference) {
            mCountDownViewWeakReference = new WeakReference<CountDownView>(countDownViewWeakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            CountDownView countDownView = mCountDownViewWeakReference.get();
            if (countDownView != null && !TextUtils.isEmpty(countDownView.mStartTime)) {
                String[] timeDifference = AppDateMgr.getTimeDifference(AppDateMgr.todayYyyyMmDdHhMmSs(), countDownView.mStartTime);
                if (timeDifference != null) {
                    countDownView.sTime = timeDifference;
                    sendEmptyMessageDelayed(EMPTY,1000);
                    countDownView.invalidate();
                }else{
                    countDownView.sTime[0] = "悬";
                    countDownView.sTime[1] = "赏";
                    countDownView.sTime[2] = "结";
                    countDownView.sTime[3] = "束";
                    countDownView.isOverTime = true;
                    countDownView.measure(0,0);
                    countDownView.initTimeBgRect();
                    countDownView.invalidate();
                    removeCallbacks(null);
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: " );
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mMyHandler != null) {
            mMyHandler.removeCallbacks(null);
        }
        super.onDetachedFromWindow();
    }
}
