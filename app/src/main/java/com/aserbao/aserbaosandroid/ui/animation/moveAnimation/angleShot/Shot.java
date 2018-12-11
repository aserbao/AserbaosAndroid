package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

import java.lang.ref.WeakReference;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/11
 * email: 1142803753@qq.com
 */
public class Shot  {
    private int time = 10;
    public int distance = 10;
    protected Bitmap mImage;
    public float mCurrentX;
    public float mCurrentY;

    public float mMaxWidth;
    public float mMaxHeight;

    private float mInitialX = 0 ;
    private float mInitialY = 0 ;

    private float mRotation;
    private float mAngle;

    private Matrix mMatrix;
    private Paint mPaint;

    private int mBitmapHalfWidth;
    private int mBitmapHalfHeight;

    public boolean isStop = false;

    public void setMaxWH(float screenHeight,float screenWidth){
        mMaxWidth = screenWidth;
        mMaxHeight = screenHeight;
    }
    /**
     * @param bitmap 图片
     * @param initX  初始值X
     * @param initY  初始值Y
     * @param angle  起始角度
     */
    protected Shot(Context context, Bitmap bitmap, float initX, float initY, float angle) {
        mBitmapHalfWidth = bitmap.getWidth();
        mBitmapHalfHeight = bitmap.getHeight();
        mMatrix = new Matrix();
        mPaint = new Paint();
        mImage = bitmap;
        mCurrentX = mInitialX = initX;
        mCurrentY = mInitialY = initY;
        mAngle = angle;
        myHandler.sendEmptyMessageDelayed(0,time);
        mMaxWidth = DisplayUtil.getScreenWidth(context);
        mMaxHeight = DisplayUtil.getScreenHeight(context);
    }

    public void draw(Canvas canvas){
        mMatrix.reset();
        mMatrix.postRotate(mRotation, mBitmapHalfWidth, mBitmapHalfHeight);
        mMatrix.postTranslate(mCurrentX, mCurrentY);
        canvas.drawBitmap(mImage,mMatrix,mPaint);
    }

    private static final String TAG = "Shot";
    public void update(){
        mCurrentX =  mCurrentX + (float)(distance * Math.cos(mAngle));
        mCurrentY =  mCurrentX - (float)(distance * Math.sin(mAngle));
        mRotation = mRotation + 10;
        if (mCurrentY > mMaxHeight || mCurrentY < 0 || mCurrentX < 0 || mCurrentX < mMaxWidth){
            stop();
            Log.e(TAG, "update: 出去了 "  );
        }
    }

    public void stop(){
        isStop = true;
        myHandler.removeMessages(0);
    }

    public MyHandler myHandler = new MyHandler(new WeakReference<Shot>(this));
    public class MyHandler extends Handler {
        public WeakReference<Shot> mWeakReference;

        public MyHandler(WeakReference<Shot> mWeakReference) {
            this.mWeakReference = mWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            Shot shot = mWeakReference.get();
            if (shot != null) {
                if (!isStop) {
                    shot.update();
                    myHandler.sendEmptyMessageDelayed(0,time);
                }
            }
        }
    }
}
