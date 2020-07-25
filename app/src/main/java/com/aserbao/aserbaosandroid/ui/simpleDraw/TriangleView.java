package com.aserbao.aserbaosandroid.ui.simpleDraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.base.utils.screen.DisplayUtil;


/*
 * 作用：绘制三角形
 * @author aserbao
 * @date: on 2020/5/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.simpleDraw
 */
public class TriangleView extends View {
    private int mWidth =0; //三角形的宽度
    private int mHeight =0; //三角形的高度

    public TriangleView(Context context) {
        super(context);
        initView();
    }
 
    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
 
    private void initView() {
        mWidth = DisplayUtil.dip2px(12);
        mHeight = DisplayUtil.dip2px(6);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#96000000"));
        paint.setAntiAlias(true); //抗锯齿
        paint.setStyle(Paint.Style.FILL);//实线
        //创建路径
        Path path = new Path();
        path.moveTo(0,mHeight);
        path.lineTo(mWidth,mHeight);
        path.lineTo(mWidth/2,0);
        path.close();//闭合路径
        //画在画布上
        canvas.drawPath(path,paint);
    }
}