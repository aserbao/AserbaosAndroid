package com.aserbao.aserbaosandroid.ui.canvas.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/18 8:39 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.canvas.canvas
 */
public class CircleView extends View{

    private Paint mPaint;
    private long mStartTime;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private Context mContext;
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mMapBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.img_dancebase_other);
    }

    private static final String TAG = "CircleView";
    @Override
    protected void onDraw(Canvas canvas) {
        mStartTime = System.currentTimeMillis();
        super.onDraw(canvas);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        for (int i = 0; i < totalCount; i++) {
//            canvas.drawCircle(canvasWidth /2, canvasHeight /2,30,mPaint); // 绘制10W次耗时0.54s
            canvasMapFrame(canvasWidth,canvasHeight,canvas,mPaint);
        }
        Log.e(TAG, "onDraw: 耗时" + (System.currentTimeMillis() - mStartTime) );
        if (callBackListener != null) {
            callBackListener.back(System.currentTimeMillis() - mStartTime);
        }
    }

    private Bitmap mMapBitmap;

    public void canvasMapFrame(int canvasWidth,int canvasHeight,Canvas canvas, Paint paint){
        Rect srcRect = new Rect(0, 0, mMapBitmap.getWidth(), mMapBitmap.getHeight());
        int margain = 50;
        Rect decRect = new Rect(margain, margain, canvasWidth - margain, canvasHeight-margain);
        canvas.drawBitmap(mMapBitmap,srcRect,decRect,paint);
    }

    public CallBackListener callBackListener;
    private int totalCount;

    public void setCallBackListener(CallBackListener callBackListener,int totalCount) {
        this.callBackListener = callBackListener;
        this.totalCount = totalCount;
        invalidate();
    }

    public interface  CallBackListener{
        void back(long time);
    }
}
