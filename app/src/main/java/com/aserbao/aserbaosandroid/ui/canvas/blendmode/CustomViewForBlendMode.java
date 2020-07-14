package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 * 参考 //[Android Xfermode 使用方法和内部原理](https://www.jianshu.com/p/2e987dcc77f5)
 * 参考 //[Canvas和PorterDuffXfermode的秘密](https://www.jianshu.com/p/70e912f21a01)
 * @author aserbao
 * @date : On 2019-08-21 20:34
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.canvas
 */
public class CustomViewForBlendMode extends View {

    public CustomViewForBlendMode(Context context) {
        this(context,null);
    }

    public CustomViewForBlendMode(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomViewForBlendMode(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomViewForBlendMode);
        final Drawable d = a.getDrawable(R.styleable.CustomViewForBlendMode_src);
//        int blend_mode = a.getInt(R.styleable.CustomViewForBlendMode_blend_mode,PorterDuff.Mode.SRC_IN);
        init();
    }


    private Paint mBottomPaint,mTopPaint;
    PorterDuffXfermode xfermode;

    public void setXfermode(PorterDuff.Mode mode) {
        this.xfermode = new PorterDuffXfermode(mode);
        mBottomPaint.setXfermode(xfermode);
        invalidate();
    }

    private void init() {
        //初始化画笔
        mBottomPaint = new Paint();
        //设置画笔颜色（不能为完全透明）
        mBottomPaint.setColor(Color.BLUE);
        mBottomPaint.setStyle(Paint.Style.FILL_AND_STROKE);// 源图像
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, 200, 200), mBottomPaint);
        canvas.scale(0.5f, 0.5f);//缩放了
        canvas.drawRect(new Rect(400, 400, 600, 600), mBottomPaint);

        canvas.translate(600, 600);//平移了
        canvas.rotate(45);//旋转了
        canvas.drawRect(new Rect(0, 0, 200, 200), mBottomPaint);

        canvas.translate(200, 200);
        canvas.skew(.5f, .5f);//扭曲了
        canvas.drawRect(new Rect(0, 0, 200, 200), mBottomPaint);
    }


    /**
     * 对正方形位图做圆形变换处理
     * @param src 正方形位图
     * @return 圆形变换后的位图
     */
    public static Bitmap circle(Bitmap src){
        int length = src.getWidth();
        Bitmap result = Bitmap.createBitmap(length,
            length, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(result);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 画圆形
        c.drawCircle(length>>1, length>>1, length>>1, p);
        // 设置 Xfermode 参数
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 画原始图片
        c.drawBitmap(src, 0, 0, p);

        return result;
    }
}
