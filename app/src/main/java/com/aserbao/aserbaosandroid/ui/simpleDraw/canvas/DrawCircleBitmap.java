package com.aserbao.aserbaosandroid.ui.simpleDraw.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;

import static android.graphics.PorterDuff.Mode.SRC_IN;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/14
 * email: 1142803753@qq.com
 */
public class DrawCircleBitmap extends View{

    public DrawCircleBitmap(Context context) {
        this(context,null);
    }

    public DrawCircleBitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawCircleBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private Paint mPaint;
    private void initData() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetPaint.setXfermode(new PorterDuffXfermode(SRC_IN));

        mSourceBitmap = BitmapFactory.decodeResource(getResources(), ImageSource.getRandomImageId());
        mTargetBitmap = Bitmap.createBitmap(mSourceBitmap.getWidth(), mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mTargetCanvas = new Canvas(mTargetBitmap);

    }

    private Paint mTargetPaint;
    private Bitmap mSourceBitmap;
    private Bitmap mTargetBitmap;
    private Canvas mTargetCanvas;

    private int mWidth;
    private int mHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 生成圆形Bitmap过程.
        int radius = Math.min(mWidth, mHeight) / 2;
        // 先绘制圆形
        mTargetCanvas.drawCircle(mWidth / 2, mHeight / 2, radius, mPaint);
        // 再绘制Bitmap
        mTargetCanvas.drawBitmap(mSourceBitmap, 0, 0, mTargetPaint);

        canvas.drawBitmap(mTargetBitmap, 0, 0, null);

    }

}
