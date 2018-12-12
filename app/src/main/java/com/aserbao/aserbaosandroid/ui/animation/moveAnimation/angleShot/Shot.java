package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

import java.lang.ref.WeakReference;

import static java.lang.Math.PI;

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

    private float shotTargetX,shotTargetY,shotTargetRadius = 0 ;

    private float mRotation;
    private float mAngle;

    private Matrix mMatrix;
    private Paint mPaint;
    private int mBitmapHalfWidth;
    private int mBitmapHalfHeight;

    private IShotListener mIshotListener;
    /**
     * @param context
     * @param bitmap        图片
     * @param initX         初始值X
     * @param initY         初始值Y
     * @param angle         起始角度
     * @param targetX       被击中物体中心点x
     * @param targetY       被击中物体中心点y
     * @param targetRadius  被击中物体的半径
     * @param iShotListener
     */
    protected Shot(Context context, Bitmap bitmap, float initX, float initY, float angle,int targetX,int targetY,int targetRadius,IShotListener iShotListener) {
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mMaxWidth = DisplayUtil.getScreenWidth(context);
        mMaxHeight = DisplayUtil.getScreenHeight(context);
        mImage = bitmap;
        mBitmapHalfWidth = bitmap.getWidth();
        mBitmapHalfHeight = bitmap.getHeight();
        mCurrentX =initX;
        mCurrentY =initY;
        mAngle = (float)(2*PI/360*angle);
        shotTargetX = targetX;
        shotTargetY = targetY;
        shotTargetRadius = targetRadius;
        mIshotListener = iShotListener;
    }

    public void draw(Canvas canvas){
        updateData();
        mMatrix.reset();
        mMatrix.postRotate(mRotation, mBitmapHalfWidth/2, mBitmapHalfHeight/2);
        mMatrix.postTranslate(mCurrentX, mCurrentY);
        canvas.drawBitmap(mImage,mMatrix,mPaint);
//        canvas.drawCircle(mCurrentX,mCurrentY,25,mPaint);
        canvas.drawCircle(shotTargetX,shotTargetY,shotTargetRadius,mPaint);
        judge();
    }

    private static final String TAG = "Shot";
    public void updateData(){
        mCurrentX =  mCurrentX + (float)(distance * Math.cos(mAngle));
        mCurrentY =  mCurrentY - (float)(distance * Math.sin(mAngle));
        Log.e(TAG, "updateData: mCurrentY = " + mCurrentY );
        mRotation = (mRotation + 10) % 360;
    }
    private void judge() {
        if (mCurrentY > mMaxHeight || mCurrentY < 0 || mCurrentX < 0 || mCurrentX > mMaxWidth){
            if (mIshotListener != null) {
                mIshotListener.isLoseEfficacy(this);
            }
        }
        if (shotTargetRadius > 0) {
            float v = (float) Math.sqrt(Math.pow(mCurrentX - shotTargetX, 2) + Math.pow(mCurrentY - shotTargetY, 2));
            if (v <= mBitmapHalfHeight + shotTargetRadius && mIshotListener!=null){
                mIshotListener.isHit(this);
            }
        }
    }


}
