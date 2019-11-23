package com.aserbao.aserbaosandroid.ui.customView.countdownView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-21 15:22
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.customView.countdownView
 */
public class ACountDownVG extends LinearLayout implements View.OnClickListener{
    public static final int INT_MODE_DEFAULT = 0;//默认模式
    public static final int INT_MODE_ONLY_TIME = 1;//只显示时间模式
    private int mCuurMode = INT_MODE_DEFAULT; //当前UI显示模式
    private static final String TAG = "CountDownVG";
    public static final int INT_START = 0;
    private TextView mMonthDayTv,mHourMinTv,mNameTv;
    public boolean mIsStart =false;
    private  TimeHandler mTimeHandler = new TimeHandler(this);

    public ACountDownVG(Context context) {
        this(context,null);
    }

    public ACountDownVG(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ACountDownVG(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ACountDownVG(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    private void initView(Context context) {
        setOnClickListener(this);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_count_down_vg, null);
        addView(view);
        mMonthDayTv = ((TextView) view.findViewById(R.id.monthDayTv));
        mHourMinTv = ((TextView) view.findViewById(R.id.hourMinTv));
        mNameTv = ((TextView) view.findViewById(R.id.nameTv));
        startCountdownTime();
    }

    public void updateTime(){
        Date date = new Date();
        String monthAndDay = new SimpleDateFormat("MM/dd", Locale.getDefault()).format(date);
        String hourAndMin = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(date);
        mMonthDayTv.setText("———— " + monthAndDay + " ————");
        mHourMinTv.setText(hourAndMin);
    };

    /**
     * 停止计时
     */
    public void stopCountDounTime(){
        mIsStart = false;
        if (mTimeHandler != null) {
            mTimeHandler.removeCallbacks(null);
        }
    }

    /**
     * 开始计时
     */
    public void startCountdownTime(){
        if (mTimeHandler == null) {
            mTimeHandler = new TimeHandler(this);
        }
        mIsStart = true;
        mTimeHandler.sendEmptyMessage(INT_START);
    }

    @Override
    public void onClick(View v) {
        mCuurMode = mCuurMode ^ 1;
        changeMode(mCuurMode);
    }

    /**
     * 修改显示模式
     * @param mode 目前有INT_MODE_DEFAULT,INT_MODE_ONLY_TIME两种模式;
     */
    private void changeMode(int mode) {
        switch (mode){
            case INT_MODE_ONLY_TIME:
                mNameTv.setVisibility(INVISIBLE);
                mMonthDayTv.setVisibility(INVISIBLE);
                break;
            default:
                mHourMinTv.setVisibility(VISIBLE);
                mNameTv.setVisibility(VISIBLE);
                mMonthDayTv.setVisibility(VISIBLE);
                break;
        }
    }

    private static class TimeHandler extends Handler{
        WeakReference<ACountDownVG> mWeakCountDownVG;

        public TimeHandler(ACountDownVG weakCountDownVG) {
            mWeakCountDownVG = new WeakReference<>(weakCountDownVG);
        }

        @Override
        public void handleMessage(Message msg) {
            ACountDownVG countDownVG = mWeakCountDownVG.get();
            if (countDownVG != null && countDownVG.mIsStart) {
                sendEmptyMessageDelayed(msg.what,1000);
                countDownVG.updateTime();
            }
        }
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopCountDounTime();
    }


}
