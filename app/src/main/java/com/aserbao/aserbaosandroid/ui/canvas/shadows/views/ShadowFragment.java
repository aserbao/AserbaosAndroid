package com.aserbao.aserbaosandroid.ui.canvas.shadows.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.base.utils.screen.DisplayUtil;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-06 16:47
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.shadows.views
 */
public class ShadowFragment extends FrameLayout {
    public ShadowFragment( Context context) {
        this(context,null);
    }

    public ShadowFragment( Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShadowFragment( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(DisplayUtil.dip2px(4),0,DisplayUtil.dip2px(2),Color.parseColor("#7e000000"));
    }

    private static final String TAG = "ShadowFragment";

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        RectF rectF = new RectF(getX() + (getWidth() / 20), getY(), getX() + getWidth() - (getWidth() / 20), getY() + getHeight() - ((getHeight() / 40)));
//        canvas.drawRoundRect(rectF, DisplayUtil.dip2px(150), DisplayUtil.dip2px(150), mPaint);

        View view = getChildAt(0);
        int width = view.getWidth();
        int height = view.getHeight();

        float x = view.getX();
        float y = view.getY();

        Log.e(TAG, "dispatchDraw: width = " +width  + " height = "+ height + " x = " + x + " y = " + y + " getWidth = " + getWidth() + " getHeight= " + getHeight()
            +" getTop = " + getTop() + " getBottom = "+ getBottom() + " \ngetMeasuredWidth = " + getMeasuredWidth()  + " getMeasuredHeight = "+ getMeasuredHeight()
            +"getPaddingLeft = "+  getPaddingLeft()+ " getPaddingTop =  " + getPaddingTop() +" getPaddingRight = "+getPaddingRight()+"  getPaddingBottom = " + getPaddingBottom());

//        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2,DisplayUtil.dip2px(15),mPaint);
        RectF rectF1 = new RectF(0, 0, getWidth()-DisplayUtil.dip2px(2), getHeight() - DisplayUtil.dip2px(5));
        canvas.drawRoundRect(rectF1,DisplayUtil.dip2px(4),DisplayUtil.dip2px(4),mPaint);
        super.dispatchDraw(canvas);
    }
}
