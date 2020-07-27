package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.screen.DisplayUtil;

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
    private static final String TAG = "CustomViewForBlendMode";
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


    private Paint mFirstPaint,mXFermodePaint,mPathPaint;
    PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    public void setXfermode(PorterDuff.Mode mode) {
        this.xfermode = new PorterDuffXfermode(mode);
        Log.e(TAG, "setXfermode: " + mode.ordinal());
        invalidate();
    }

    private void init() {
        //初始化画笔
        mFirstPaint = createPaint(Color.BLUE);
        mXFermodePaint = createPaint(Color.RED);
        mPathPaint = createPaint(Color.RED);
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
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);
//        handlerXFerMode(canvas);
        drawPath(canvas);

//        handlerCanvasMethod(canvas);
    }

    /**
     * 处理canvas的方法
     * @param canvas
     */
    private void handlerCanvasMethod(Canvas canvas){
        int top = 100;
        canvas.drawRect(new Rect(0, top, 200, 200+top), mFirstPaint);
        canvas.scale(0.5f, 0.5f);//缩放了
        canvas.drawRect(new Rect(400, 400+top, 600, 600+top), mFirstPaint);

        canvas.translate(600, 600);//平移了
        canvas.rotate(45);//旋转了
        canvas.drawRect(new Rect(0, 0, 200, 200), mFirstPaint);

        canvas.translate(200, 200);
        canvas.skew(.5f, .5f);//扭曲了
        canvas.drawRect(new Rect(0, 0, 200, 200), mFirstPaint);
    }

    /**
     * 处理xferMode
     * @param canvas
     */
    private void handlerXFerMode(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
            int r = canvasWidth / 3;

            //绘制黄色的圆形
            mXFermodePaint.setColor(0xFFFFCC44);
            canvas.drawCircle(r, r, r, mXFermodePaint);

            //绘制蓝色的矩形
            mXFermodePaint.setColor(0xFF66AAFF);
            mXFermodePaint.setXfermode(xfermode);
            canvas.drawRect(r, r, r * 2.7f, r * 2.7f, mXFermodePaint);
            mXFermodePaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    int dp2 = DisplayUtil.dp2px(2);
    int dp8 = DisplayUtil.dp2px(8);
    int dp32 = DisplayUtil.dp2px(32);
    int dp50 = DisplayUtil.dp2px(50);
    int dp64 = DisplayUtil.dp2px(64);
    int dp100 = DisplayUtil.dp2px(100);
    int dp300 = DisplayUtil.dp2px(300);
    /**
     * 绘制多边形
     * @param canvas 画布
     */
    private void drawPath(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        Path path = new Path();
        int left = (canvasWidth - dp100)/2;
        int top = (canvasHeight - dp100)/2;
        int rigth = left + dp100 * 2;
        int bottom = top + dp100;
        RectF rectF = new RectF(left,top,rigth,bottom);
        path.addRoundRect(rectF,
            new float[]{
                dp2, dp2,
                dp8,dp8,
                dp32, dp32,
                dp64, dp64}, Path.Direction.CW);
        path.close();

        mPathPaint.setColor(Color.RED);
        canvas.drawPath(path, mPathPaint);
//        canvas.drawRoundRect(rectF,dp8,dp8,mPathPaint);
        //  设置像素融合模式
        mPathPaint.setXfermode(xfermode);
        mPathPaint.setColor(Color.BLUE);
        int radius = dp64;
        canvas.drawCircle(left+ dp50,top+ dp50,radius,mPathPaint);
//        rectF.right = left + dp100;
//        canvas.drawRect(rectF,mPathPaint);
        mPathPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
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
