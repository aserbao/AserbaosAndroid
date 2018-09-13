package com.aserbao.aserbaosandroid.AUtils.AUI.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/13
 * email: 1142803753@qq.com
 */
public class ACustomRecordProgress extends View {

    private Paint mPaint;

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
