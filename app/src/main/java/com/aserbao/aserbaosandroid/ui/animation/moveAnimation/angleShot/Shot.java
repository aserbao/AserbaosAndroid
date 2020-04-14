package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.base.utils.screen.DisplayUtil;

import java.util.List;

import static java.lang.Math.PI;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/11
 * email: this is empty email
 */
public class Shot  {
    public int distance = 50;
    protected Bitmap mImage;
    public float mCurrentX;
    public float mCurrentY;
    private Context mContext;
    public float mMaxWidth;
    public float mMaxHeight;

    private float shotTargetX,shotTargetY,shotTargetRadius = 0 ;

    private float mRotation;
    private float mDetailAngle,mInitAngle; //mDetailAngle(和水平线夹角 0-90)，mInitAngle(和水平线夹角 0 - 360)

    private Matrix mMatrix;
    private Paint mPaint;
    private int mBitmapHalfWidth;
    private int mBitmapHalfHeight;

    private IShotListener mIshotListener;
    private List<TargetView> mTargetViewList;
    private int angleType; // 判断角度在第几象限
    

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
    protected Shot(Context context, Bitmap bitmap, float initX, float initY, float angle,int targetX,int targetY,int targetRadius,IShotListener iShotListener,List<TargetView> targetViewList) {
        mContext = context;
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mMaxWidth = DisplayUtil.getScreenWidth(context);
        mMaxHeight = DisplayUtil.getScreenHeight(context);
        mImage = bitmap;
        mBitmapHalfWidth = bitmap.getWidth() / 2;
//        mBitmapHalfHeight = bitmap.getHeight()/2;
        mBitmapHalfHeight = 30;
        mCurrentX =initX;
        mCurrentY =initY;
        calculateAngle(angle);
        shotTargetX = targetX;
        shotTargetY = targetY;
        shotTargetRadius = targetRadius;
        mIshotListener = iShotListener;
        mTargetViewList = targetViewList;
    }

    public void calculateAngle(float angle){
        angleType = (int)angle / 90;
        mInitAngle = angle;
        mDetailAngle = mInitAngle % 90;
        mDetailAngle = (float)(2*PI/360* mDetailAngle);
    }

    public void draw(Canvas canvas){
        updateData();
        /*mMatrix.reset();
        mMatrix.postRotate(mRotation, mBitmapHalfWidth, mBitmapHalfHeight);
        mMatrix.postTranslate(mCurrentX, mCurrentY);
        canvas.drawBitmap(mImage,mMatrix,mPaint);*/
        canvas.drawCircle(mCurrentX,mCurrentY,mBitmapHalfHeight,mPaint);
        canvas.drawCircle(shotTargetX,shotTargetY,shotTargetRadius,mPaint);
        for (int i = 0; i < mTargetViewList.size(); i++) {
            float[] floats = mTargetViewList.get(i).getFloats();
            canvas.drawCircle(floats[0],floats[1],floats[2],mPaint);
        }
        judge();
    }

    private static final String TAG = "Shot";
    public void updateData(){
        if(angleType == 0){
            mCurrentX = mCurrentX + (float) (distance * Math.cos(mDetailAngle));
            mCurrentY =  mCurrentY - (float)(distance * Math.sin(mDetailAngle));
        }else if(angleType == 1){
            mCurrentX = mCurrentX - (float) (distance * Math.sin(mDetailAngle));
            mCurrentY =  mCurrentY - (float)(distance * Math.cos(mDetailAngle));
        }else if(angleType == 2){
            mCurrentX = mCurrentX - (float) (distance * Math.cos(mDetailAngle));
            mCurrentY =  mCurrentY + (float)(distance * Math.sin(mDetailAngle));
        }else{
            mCurrentX = mCurrentX + (float) (distance * Math.sin(mDetailAngle));
            mCurrentY =  mCurrentY + (float)(distance * Math.cos(mDetailAngle));
        }
        mRotation = (mRotation + 10) % 360;
    }


    private void judge() {
        springback();
        /*if (shotTargetRadius > 0) {
            float v = (float) Math.sqrt(Math.pow(mCurrentX - shotTargetX, 2) + Math.pow(mCurrentY - shotTargetY, 2));
            if (v <= mBitmapHalfHeight + shotTargetRadius && mIshotListener!=null){
                mIshotListener.isHit(this);
            }
        }*/

        for (int i = 0; i < mTargetViewList.size(); i++) {
            TargetView targetView = mTargetViewList.get(i);
            float[] floats = targetView.getFloats();
            float v = (float) Math.sqrt(Math.pow(mCurrentX - floats[0], 2) + Math.pow(mCurrentY - floats[1], 2));
            if (v <= mBitmapHalfHeight + shotTargetRadius && mIshotListener!=null){
                mIshotListener.isHit(this,targetView);
                mTargetViewList.remove(targetView);
                break;
            }
        }
    }
    private boolean isNeedSpringBack = true;
    private int mMaxSpringTime = 10;
    private int mCuurSpringTime = 0;

    /**
     * 角度反射
     */
    public void springback(){
        if (isNeedSpringBack & mCuurSpringTime < mMaxSpringTime){
            if (mCurrentY - mBitmapHalfHeight <= 0 || mCurrentY + mBitmapHalfHeight >= mMaxHeight){
                mInitAngle = 360 - mInitAngle;
                calculateAngle(mInitAngle);
                mCuurSpringTime ++;
            }else if(mCurrentX - mBitmapHalfHeight <= 0){
                if (mInitAngle >= 90 && mInitAngle <= 180){
                    mInitAngle = 180 - mInitAngle;
                }else if(mInitAngle > 180 && mInitAngle < 270){
                    mInitAngle = 540 - mInitAngle;
                }
                calculateAngle(mInitAngle);
                mCuurSpringTime ++;
            }else if(mCurrentX + mBitmapHalfHeight >= mMaxWidth){
                if (mInitAngle <= 90){
                    mInitAngle = 180 - mInitAngle;
                }else if(mInitAngle > 270 && mInitAngle < 360){
                    mInitAngle = 540 - mInitAngle;
                }
                calculateAngle(mInitAngle);
                mCuurSpringTime ++;
            }
        }else{
            if (mCurrentY > mMaxHeight || mCurrentY < 0 || mCurrentX < 0 || mCurrentX > mMaxWidth){
                if (mIshotListener != null) {
                    mIshotListener.isLoseEfficacy(this);
                }
            }
        }
    }


}
