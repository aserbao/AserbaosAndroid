package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-09-23 10:49
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation
 */
public class CatchTouchCardView  extends CardView {
    private static final String TAG = "CatchTouchCardView";
    private float mStartRawX;
    private float mStartX;
    private boolean mIsScrolling = false;
    private float difRawX;
    public static final int CATCH_TOUCH_CARF_VIEW = 0;

    public CatchTouchCardView(@NonNull Context context) {
        this(context,null);
    }

    public CatchTouchCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CatchTouchCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartRawX = event.getRawX();
                mStartX = event.getX();
                mIsScrolling = false;
                if (mIItemOnTouchCallBackListener != null) {
                    mIItemOnTouchCallBackListener.onScollView(this,0,event.getAction(), CATCH_TOUCH_CARF_VIEW);
                }
                super.dispatchTouchEvent(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                mIsScrolling = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                difRawX = event.getRawX() - mStartRawX;
                float difX = event.getX() - mStartX;
                useAnimation(difRawX,event.getAction());
                Log.e(TAG, "dispatchTouchEvent: " +  getX() + " \n difRawX = " + difRawX + " difX = " + difX);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                mIsScrolling = false;
                if (difRawX < -AserbaoApplication.screenWidth / 3) {
                    useAnimation(-AserbaoApplication.screenWidth,event.getAction());
                }else {
                    useAnimation(0,event.getAction());
                }
                break;
        }
        Log.e(TAG, "dispatchTouchEvent: " + event.getAction() + " " +  getX() );
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mIsScrolling || super.onInterceptTouchEvent(event);
    }



    public SlideItemAnimationAdapter.IItemOnTouchCallBackListener mIItemOnTouchCallBackListener;
    public void setIItemOnTouchCallBackListener(SlideItemAnimationAdapter.IItemOnTouchCallBackListener itemOnTouchCallBackListener){
        mIItemOnTouchCallBackListener = itemOnTouchCallBackListener;
    }


    public void useAnimation(float finalX,int  action) {
        if (mIItemOnTouchCallBackListener != null) {
            mIItemOnTouchCallBackListener.onScollView(this,finalX,action,CATCH_TOUCH_CARF_VIEW);
        }
    }



}
