package com.aserbao.aserbaosandroid.ui.customView.gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/7/3 4:23 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.customView.gradient
 */
public class LinearGradientView extends View {

    private Paint mPaint;
    private RectF mRectF;

    public LinearGradientView(Context context) {
        this(context,null);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private int mStartColor = Color.WHITE;
    private int mEndColor = Color.BLACK;

    public void setColor(int startColor,int endColor){
        mStartColor = startColor;
        mEndColor = endColor;
        invalidate();
    }
    private Shader.TileMode mTileMode = Shader.TileMode.CLAMP;
    public void changeTileMode(Shader.TileMode tileMode){
        mTileMode = tileMode;
        invalidate();
    }

    private static final String TAG = "LinearGradientView";

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        mRectF = new RectF(0, 0, measuredWidth, measuredHeight);
        Log.e(TAG, "onMeasure: measuredHeight = " + measuredHeight + "  measureWidth" + measuredWidth + "\n getWidth = " + getWidth() + "  getheight = " + getHeight() );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient  linearGradient = new LinearGradient(0, 0,0, mRectF.bottom,mStartColor,  mEndColor,mTileMode);
//        LinearGradient  linearGradient = new LinearGradient(0, 0,mRectF.right/2,  mRectF.bottom/2,mStartColor,  mEndColor,mTileMode);
        mPaint = new Paint();
        mPaint.setShader(linearGradient);
        canvas.drawRect(mRectF, mPaint);
        super.onDraw(canvas);
    }
}
