package com.aserbao.aserbaosandroid.ui.canvas.shadows.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-06 16:10
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.shadows.
 */
public class ShadowTextView extends TextView {
    public ShadowTextView(Context context) {
        this(context,null);
    }

    public ShadowTextView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShadowTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(DisplayUtil.dip2px(4),0,DisplayUtil.dip2px(4),Color.BLACK);
    }

    private static final String TAG = "ShadowTextView";

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e(TAG, "dispatchDraw:  getWidth = " + getWidth() + " getHeight= " + getHeight()
            +" getTop = " + getTop() + " getBottom = "+ getBottom() + " \ngetMeasuredWidth = " + getMeasuredWidth()  + " getMeasuredHeight = "+ getMeasuredHeight()
            +"getPaddingLeft = "+  getPaddingLeft()+ " getPaddingTop =  " + getPaddingTop() +" getPaddingRight = "+getPaddingRight()+"  getPaddingBottom = " + getPaddingBottom());

//        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
//
//        canvas.drawRoundRect(rectF, DisplayUtil.dip2px(15), DisplayUtil.dip2px(15), mPaint);

//        canvas.drawCircle(getWidth()/2, getHeight()/2,DisplayUtil.dip2px(15),mPaint);
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, DisplayUtil.dip2px(15), DisplayUtil.dip2px(15), mPaint);
        super.onDraw(canvas);
    }
}
