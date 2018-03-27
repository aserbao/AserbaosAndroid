package com.aserbao.aserbaosandroid.customView.countdownView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.DisplayUtil;

/**
 * description:
 * Created by aserbao on 2018/3/27.
 */


public class CountDownView extends View {
    private static final String DEFAULT_SUFFIX = ":";
    private static final String HOUR = "07";
    protected Paint mTimeTextPaint, mSuffixTextPaint, mMeasureHourWidthPaint,mTimeBgPaint;
    private RectF mDayBgRectF;
    private Context mContext;

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
        mTimeTextPaint.setTextSize(DisplayUtil.sp2px(mContext,12));
        mTimeTextPaint.setFakeBoldText(true);


        mTimeBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimeBgPaint.setStyle(Paint.Style.FILL);
        mTimeBgPaint.setColor(Color.parseColor("#F64646"));

    }

    private void initTimeBgRect() {
        mDayBgRectF = new RectF(0,0,80,80);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(DEFAULT_SUFFIX,100,150,mTimeTextPaint);
        canvas.drawRoundRect(mDayBgRectF,10,10,mTimeBgPaint);
        super.onDraw(canvas);
    }
}
