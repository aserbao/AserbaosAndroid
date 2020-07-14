package com.aserbao.aserbaosandroid.ui.canvas.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.screen.DisplayUtil;

import java.lang.ref.WeakReference;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/14
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.canvas.canvas
 */
public class ProgressView extends View {

    private Paint mProgressPaint;
    private Paint mActivePaint;
    private Paint mPathPaint;

    private int cuurDuration,maxDuration = 10*1000;
//    int startColor = Color.parseColor("#D7F660");
//    int endColor = Color.parseColor("#FDFF0D");
    int startColor = Color.BLACK;
    int endColor = Color.WHITE;
    private Path mPath;
    int dp4 = DisplayUtil.dip2px(4);
    private int leftTopRadius = dp4, leftBottomRadius= dp4, rightTopRadius =dp4, rightBottomRadius =dp4;
    private RectF tempRectF = new RectF();

    public ProgressView(Context paramContext) {
        super(paramContext);
        init();
        
    }

    public ProgressView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public ProgressView(Context paramContext, AttributeSet paramAttributeSet,
                        int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    private void init() {
//        setBackgroundColor(Color.GREEN);
        mProgressPaint = new Paint();
        mActivePaint = new Paint();
        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();

        mActivePaint.setColor(Color.RED);
        mActivePaint.setStyle(Paint.Style.FILL);

        mProgressPaint.setColor(Color.parseColor("#FAE000"));
        mProgressPaint.setStyle(Paint.Style.FILL);
//        mProgressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mPathPaint.setColor(Color.TRANSPARENT);
        mPathPaint.setAntiAlias(true);
        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getMeasuredWidth(), height = getMeasuredHeight();
        int left = 0, right = 0, duration = 0;
        right = left
            + (int) (cuurDuration * 1.0F / maxDuration * width);
//        canvas.drawRoundRect(left, 0.0F, width, height,100,100, mActivePaint);

        final float halfWidth = getWidth() / 2f;
        final float halfHeight = getHeight() / 2f;
        final float minSize = halfWidth > halfHeight ? halfWidth : halfHeight;
        final float leftTop = width > 2 * leftTopRadius ? leftTopRadius : minSize / 2f;
        final float rightTop = width > 2 * rightTopRadius ? rightTopRadius : minSize / 2f;
        final float leftBottom = width > 2 * leftBottomRadius ? leftBottomRadius : minSize / 2f;
        final float rightBottom = width > 2 * rightBottomRadius ? rightBottomRadius : minSize / 2f;

        canvas.drawRect(left, 0.0F, right, height,
            mProgressPaint);

//        tryOne(canvas, leftTop, rightTop, leftBottom, rightBottom);
        tryTwo(canvas, leftTop, rightTop, leftBottom, rightBottom);


//        Shader shader = new LinearGradient(0, 0, right, 0, startColor, endColor, Shader.TileMode.CLAMP);
//        mProgressPaint.setShader(shader);
    }

    private void tryOne(Canvas canvas, float leftTop, float rightTop, float leftBottom, float rightBottom) {
        mPath.reset();
        mPath.moveTo(0, leftTop/* / 2f*/);
        tempRectF.set(0, 0, leftTop * 2, leftTop * 2);
        mPath.arcTo(tempRectF, 180, 90, false);
        mPath.lineTo(0, 0);
        mPath.lineTo(0, leftTop/* / 2f*/);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(getWidth() - rightTop/* / 2f*/, 0);
        tempRectF.set(getWidth() - rightTop * 2, 0, getWidth(), rightTop * 2);
        mPath.arcTo(tempRectF, 270, 90, false);
        mPath.lineTo(getWidth(), 0);
        mPath.lineTo(getWidth() - rightTop/* / 2f*/, 0);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
        tempRectF.set(getWidth() - rightBottom * 2, getHeight() - rightBottom * 2, getWidth(), getHeight());
        mPath.arcTo(tempRectF, 0, 90, false);
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(leftBottom/* / 2f*/, getHeight());
        tempRectF.set(0, getHeight() - leftBottom * 2, leftBottom * 2, getHeight());
        mPath.arcTo(tempRectF, 90, 90, false);
        mPath.lineTo(0, getHeight());
        mPath.lineTo(leftBottom/* / 2f*/, getHeight());
        canvas.drawPath(mPath, mPathPaint);
    }

    private void tryTwo(Canvas canvas, float leftTop, float rightTop, float leftBottom, float rightBottom) {
        mPath.reset();
        mPath.moveTo(0, leftTop/* / 2f*/);
        tempRectF.set(0, 0, leftTop * 2, leftTop * 2);
        mPath.arcTo(tempRectF, 180, 90, false);
        mPath.lineTo(0, 0);
        mPath.lineTo(0, leftTop/* / 2f*/);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(getWidth() - rightTop/* / 2f*/, 0);
        tempRectF.set(getWidth() - rightTop * 2, 0, getWidth(), rightTop * 2);
        mPath.arcTo(tempRectF, 270, 90, false);
        mPath.lineTo(getWidth(), 0);
        mPath.lineTo(getWidth() - rightTop/* / 2f*/, 0);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
        tempRectF.set(getWidth() - rightBottom * 2, getHeight() - rightBottom * 2, getWidth(), getHeight());
        mPath.arcTo(tempRectF, 0, 90, false);
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
        canvas.drawPath(mPath, mPathPaint);

        mPath.reset();
        mPath.moveTo(leftBottom/* / 2f*/, getHeight());
        tempRectF.set(0, getHeight() - leftBottom * 2, leftBottom * 2, getHeight());
        mPath.arcTo(tempRectF, 90, 90, false);
        mPath.lineTo(0, getHeight());
        mPath.lineTo(leftBottom/* / 2f*/, getHeight());
        canvas.drawPath(mPath, mPathPaint);
    }



    public void changeDuration(int progress){
        mHandler.sendEmptyMessage(0);
        cuurDuration = progress;
        invalidate();
    }

    private Handler mHandler = new MyHandler(new WeakReference<ProgressView>(this));

    private static class MyHandler extends Handler {
        WeakReference<ProgressView> mWeakReference;

        public MyHandler(WeakReference<ProgressView> weakReference) {
            mWeakReference = weakReference;
        }

        @Override
        public void dispatchMessage(Message msg) {
            ProgressView progressView = mWeakReference.get();
            switch (msg.what) {
                case 0:
                    progressView.cuurDuration = progressView.cuurDuration + 500;
                    progressView.cuurDuration = progressView.cuurDuration % progressView.maxDuration;
                    sendEmptyMessageDelayed(0, 200);
                    progressView.invalidate();
                    break;
            }
        }
    }

}
