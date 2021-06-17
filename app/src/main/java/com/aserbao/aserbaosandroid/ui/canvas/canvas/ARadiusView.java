package com.aserbao.aserbaosandroid.ui.canvas.canvas;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.example.base.utils.screen.DisplayUtil;


/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/14
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.canvas.canvas
 * @description: 自定义圆角的ViewGroup
 */
public class ARadiusView extends FrameLayout {

    private RectF tempRectF = new RectF();
    private Rect tempRect = new Rect();
    int dp4 = DisplayUtil.dip2px(24);
    private int leftTopRadius = dp4, leftBottomRadius= dp4, rightTopRadius =dp4, rightBottomRadius =dp4;

    Path leftTopPath = new Path();
    Path rightTopPath = new Path();
    Path leftBottomPath = new Path();
    Path rightBottomPath = new Path();
    private Paint beforePaint;
    private Paint coverPaint;
    private boolean hasSet;

    public ARadiusView(@NonNull Context context) {
        super(context);
        init(context, null);

    }

    public ARadiusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public ARadiusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (Build.VERSION.SDK_INT >= 11 || isInEditMode()) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        coverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        coverPaint.setColor(Color.TRANSPARENT);
        coverPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        beforePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

    }


    @Override
    public void draw(Canvas canvas) {
        final boolean drawRadius = !(leftTopRadius == 0 && leftBottomRadius == 0
                && rightTopRadius == 0 && rightBottomRadius == 0);
        int saveCount = 0;
        super.draw(canvas);

        if (drawRadius) {
//            canvas.restoreToCount(saveCount);
            saveCount = canvas.save();
            final int width = getWidth();
            final float halfWidth = getWidth() / 2f;
            final float halfHeight = getHeight() / 2f;
            final float minSize = halfWidth > halfHeight ? halfWidth : halfHeight;
            final float leftTop = width > 2 * leftTopRadius ? leftTopRadius : minSize / 2f;
            final float rightTop = width > 2 * rightTopRadius ? rightTopRadius : minSize / 2f;
            final float leftBottom = width > 2 * leftBottomRadius ? leftBottomRadius : minSize / 2f;
            final float rightBottom = width > 2 * rightBottomRadius ? rightBottomRadius : minSize / 2f;
            leftTopPath.reset();
            leftTopPath.moveTo(0, leftTop/* / 2f*/);
            tempRectF.set(0, 0, leftTop * 2, leftTop * 2);
            leftTopPath.arcTo(tempRectF, 180, 90, false);
            leftTopPath.lineTo(0, 0);
            leftTopPath.lineTo(0, leftTop/* / 2f*/);
            canvas.drawPath(leftTopPath, coverPaint);

            rightTopPath.reset();
            rightTopPath.moveTo(getWidth() - rightTop/* / 2f*/, 0);
            tempRectF.set(getWidth() - rightTop * 2, 0, getWidth(), rightTop * 2);
            rightTopPath.arcTo(tempRectF, 270, 90, false);
            rightTopPath.lineTo(getWidth(), 0);
            rightTopPath.lineTo(getWidth() - rightTop/* / 2f*/, 0);
            canvas.drawPath(rightTopPath, coverPaint);

            rightBottomPath.reset();
            rightBottomPath.moveTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
            tempRectF.set(getWidth() - rightBottom * 2, getHeight() - rightBottom * 2, getWidth(), getHeight());
            rightBottomPath.arcTo(tempRectF, 0, 90, false);
            rightBottomPath.lineTo(getWidth(), getHeight());
            rightBottomPath.lineTo(getWidth(), getHeight() - rightBottom/* / 2f*/);
            canvas.drawPath(rightBottomPath, coverPaint);

            leftBottomPath.reset();
            leftBottomPath.moveTo(leftBottom/* / 2f*/, getHeight());
            tempRectF.set(0, getHeight() - leftBottom * 2, leftBottom * 2, getHeight());
            leftBottomPath.arcTo(tempRectF, 90, 90, false);
            leftBottomPath.lineTo(0, getHeight());
            leftBottomPath.lineTo(leftBottom/* / 2f*/, getHeight());
            canvas.drawPath(leftBottomPath, coverPaint);

            canvas.restoreToCount(saveCount);

        }
    }




    @Override
    public void setBackground(Drawable background) {
        if (background instanceof ColorDrawable) {
            if (((ColorDrawable) background).getAlpha() == 255) {
                background.setAlpha(254);
                background = new ColorDrawable(((ColorDrawable) background).getColor());
            }
        }
        super.setBackground(background);
    }

    @Override public void setBackgroundDrawable(Drawable background) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            if (background instanceof ColorDrawable) {
                if (((ColorDrawable) background).getAlpha() == 255) {
                    background.setAlpha(254);
                    background = new ColorDrawable(((ColorDrawable) background).getColor());
                }
            }
        }
        super.setBackgroundDrawable(background);
    }

}
