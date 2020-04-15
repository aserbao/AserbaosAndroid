package com.aserbao.aserbaosandroid.ui.canvas.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.example.base.utils.screen.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-27 19:55
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.canvas
 */
public class CanvasPointView  extends View {

    private Paint paint;

    public CanvasPointView(Context context) {
        this(context,null);
    }

    public CanvasPointView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasPointView(Context context,  AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public CanvasPointView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private List<Point> mPoint = new ArrayList<>();

    public void addPoint(Point point){
        if (mPoint != null) {
            mPoint.add(point);
        }
        invalidate();
    }

    public void clearPoint(){
        if (mPoint != null) {
            mPoint.clear();
        }
        invalidate();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mPoint.size(); i++) {
            Point point = mPoint.get(i);
            canvas.drawCircle(point.x,point.y, DisplayUtil.dp2px(2),paint);
        }
        super.onDraw(canvas);
    }
}
