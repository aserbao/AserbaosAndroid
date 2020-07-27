package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.screen.DisplayUtil;

/**
 * 功能: canvas 的裁剪clipRect
 * @author aserbao
 * @date : On 2019-08-21 20:34
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.canvas
 */
public class CanvasClip extends View {
    private static final String TAG = "CustomViewForBlendMode";
    public CanvasClip(Context context) {
        this(context,null);
    }

    public CanvasClip(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasClip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomViewForBlendMode);
        final Drawable d = a.getDrawable(R.styleable.CustomViewForBlendMode_src);
//        int blend_mode = a.getInt(R.styleable.CustomViewForBlendMode_blend_mode,PorterDuff.Mode.SRC_IN);
        init();
    }


    private Paint mClipPaint;

    public void setXfermode(PorterDuff.Mode mode) {
        Log.e(TAG, "setXfermode: " + mode.ordinal());
        invalidate();
    }

    private void init() {
        //初始化画笔
        mClipPaint = createPaint(Color.YELLOW);
    }

    public Paint createPaint(int color){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClipProgress(canvas);
    }

    int dp2 = DisplayUtil.dp2px(2);
    int dp8 = DisplayUtil.dp2px(8);
    int dp32 = DisplayUtil.dp2px(32);
    int dp50 = DisplayUtil.dp2px(50);
    int dp64 = DisplayUtil.dp2px(64);
    int dp100 = DisplayUtil.dp2px(100);
    int dp300 = DisplayUtil.dp2px(300);


    /**
     * 测试canvas的clipRect方法
     */
    public void drawClipProgress(Canvas canvas){
        RectF rectF = new RectF(0, 0, 300, 300);
        mClipPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mClipPaint);
        RectF rectF1 = new RectF(200, 200, 300, 300);
        mClipPaint.setColor(Color.RED);
        canvas.drawRect(rectF1,mClipPaint);


        canvas.save();
        canvas.translate(560, 0);
//        canvas.clipRect(0, 0, 300, 300);
//        canvas.clipRect(200, 200, 500, 500, Region.Op.DIFFERENCE);
//        canvas.clipRect(200, 200, 500, 500, Region.Op.INTERSECT);
//        canvas.clipRect(200, 200, 500, 500, Region.Op.UNION);
        canvas.clipRect(200, 200, 500, 500, Region.Op.XOR);
//        canvas.clipRect(200, 200, 500, 500, Region.Op.REVERSE_DIFFERENCE);
//        canvas.clipRect(200, 200, 500, 500, Region.Op.REPLACE);
//        drawScene(canvas);
        canvas.restore();
    }


    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 500, 500);

        canvas.drawColor(Color.WHITE);

        mClipPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 300, 300, mClipPaint);

        mClipPaint.setColor(Color.WHITE);
        mClipPaint.setTextSize(40);
        canvas.drawText("A", 140, 140, mClipPaint);

        mClipPaint.setColor(Color.GREEN);
        canvas.drawRect(200, 200, 500, 500, mClipPaint);

        mClipPaint.setColor(Color.WHITE);
        canvas.drawText("B", 350, 350, mClipPaint);

        mClipPaint.setColor(Color.RED);
        canvas.drawRect(200, 200, 300, 300, mClipPaint);

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
