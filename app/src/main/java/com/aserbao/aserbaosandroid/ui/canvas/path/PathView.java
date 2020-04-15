package com.aserbao.aserbaosandroid.ui.canvas.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.base.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.AserbaoApplication;

/**
 * 功能:用这个来测试path功能
 * @author aserbao
 * @date : On 2019-08-26 18:22
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.path
 */
public class PathView extends View {

    private Paint mPaint;
    private Path path;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView(Context context,  AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public PathView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(DisplayUtil.dip2px(2));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        path = new Path();
        path.moveTo(0,0);
        int screenWidth = AserbaoApplication.screenWidth/2;
        int screenHeight = AserbaoApplication.screenHeight/2;
        path.lineTo(screenWidth /2, screenHeight);
        path.lineTo(screenWidth,screenHeight/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,mPaint);
        super.onDraw(canvas);
    }
}
