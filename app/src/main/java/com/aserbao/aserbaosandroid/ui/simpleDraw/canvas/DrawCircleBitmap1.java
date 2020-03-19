package com.aserbao.aserbaosandroid.ui.simpleDraw.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import static android.graphics.PorterDuff.Mode.SRC_IN;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/14
 * email: this is empty email
 */

public class DrawCircleBitmap1 extends View{

    public DrawCircleBitmap1(Context context) {
        this(context,null);
    }

    public DrawCircleBitmap1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawCircleBitmap1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }


    private void initData() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetPaint.setXfermode(new PorterDuffXfermode(SRC_IN));

//        mSourceBitmap = BitmapFactory.decodeResource(getResources(), ASourceUtil.getRandomImageId());
        mSourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.starry_sky_5);
        Glide.with(this).load("http://ppic.getremark.com/109c6a12d65f9296d0011e0e387a6b16-c899d76e-2ec6-44c5-a345-a19461b51c47-f1143f5fffdfc22d0179698d3365f513")
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (resource != null) {
                            mSourceBitmap = ((BitmapDrawable) resource).getBitmap();
                            invalidate();
                        }
                    }
                });
        mTargetBitmap = Bitmap.createBitmap(mSourceBitmap.getWidth(), mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mTargetCanvas = new Canvas(mTargetBitmap);

    }

    private Paint mPaint;
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

//        mPaint.setColor(Color.parseColor("#FF0000"));
        mTargetCanvas.drawCircle(mWidth / 2, mHeight / 2, radius, mPaint);
        // 再绘制Bitmap
        mTargetCanvas.drawBitmap(mSourceBitmap, 0, 0, mTargetPaint);

        canvas.drawBitmap(mTargetBitmap, 0, 0, null);
    /*    int imagePadding = 0;
        Rect src = new Rect(0,0,mTargetBitmap.getWidth(),mTargetBitmap.getHeight());

        Rect dst = new Rect(imagePadding,imagePadding,getWidth() - imagePadding,getHeight() -imagePadding);
        canvas.drawBitmap(mTargetBitmap,src,dst,mPaint);
*/

       /* mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        int r = 5;
        canvas.drawArc(r,r,getWidth() - r,getHeight() -r,-90,180,false,mPaint);*/

    }

}
