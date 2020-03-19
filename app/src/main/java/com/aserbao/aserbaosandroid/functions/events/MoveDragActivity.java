package com.aserbao.aserbaosandroid.functions.events;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.AserbaoApplication;


/**
 * 使用方法：
 * 自定义Activity继承MoveDragActivity,主题windowBackground为透明，windowIsTranslucent为true
 * 重写getContentView，返回顶层父布局
 */
public abstract class MoveDragActivity extends AppCompatActivity {
    private static final String TAG = MoveDragActivity.class.getSimpleName();

    protected int mReplyViewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundDrawable(null);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;

        mReplyViewTop = AserbaoApplication.screenHeight - DisplayUtil.dip2px(100);
    }


    private float mLastScale, mLastPositionY,mLastPositionX, mLastPercentAlpha, mLastRotateY;
    private long mStartClickDownTime, mLastClickDownTime, mLastStartClickTime;//点击时间

    private int mScreenWidth;
    private int mScreenHeight;
    private float mLastX;
    private float mLastY;
    private boolean onStartDown = true;//开始触摸
    private boolean isUpDownTouch = true;//是否是上下滑动
    private boolean isLongClick,isDrag;//是否是常按,是否拖动
    private boolean isSureLeftOrRight = true;

    private int downX, downY;

    private boolean isDragDown;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean onTouchEvent = true;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                onUserInteraction();
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                // 如果点的地方是输入框位置，就不执行拖拽动画
                if( downY >= mReplyViewTop ){
                    return super.dispatchTouchEvent(ev);
                }else {
                }
                onTouchEvent = onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (ev.getX() - downX);
                int deltaY = (int) (ev.getY() - downY);
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    isDragDown = true;
                }

                if (isDragDown) {
                    // 判断滑动方向
                    // 竖直方向调用此方法， 横向不调用此方法来处理事件冲突。
                    onTouchEvent = onTouchEvent(ev);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                onTouchEvent = onTouchEvent(ev);
                isDragDown = false;
                break;
        }
        if (!isDragDown && getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent;
//        return super.dispatchTouchEvent(ev);
    }

    private Handler longClickHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isDrag) {
                Object obj = msg.obj;
                onViewLongClick((MotionEvent) obj);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 如果点的地方是输入框位置，就不执行拖拽动画
        if( downY >= mReplyViewTop ){
            return super.onTouchEvent(event);
        }else {
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartClickDownTime = System.currentTimeMillis();
                mLastX = event.getX();
                mLastY = event.getY();
                onStartDown = true;
                mLastScale = 1.0f;
                mLastPositionY = 0.0f;
                mLastRotateY = 0.0f;
                isLongClick = false;
                isDrag = false;
                isSureLeftOrRight = true;
                Message message = new Message();
                message.obj = event;
                longClickHandler.sendMessageDelayed(message, 400);
                break;
            case MotionEvent.ACTION_MOVE:
                float chaX = event.getX() - mLastX;
                float chaY = event.getY() - mLastY;
                long l = System.currentTimeMillis() - mStartClickDownTime;
                if (onStartDown) {
                    onStartDown = false;
                    if (l > 200 && Math.abs(chaX) < 10 && Math.abs(chaY) < 10) {
                        isLongClick = true;
                    }
                }
                if(Math.abs(chaX) > 20 && Math.abs(chaY) > 20){
                    isDrag = true;
                }

                if(isSureLeftOrRight && l > 300){
                    isSureLeftOrRight = false;
                    if (Math.abs(chaX) < Math.abs(chaY)) {
                        isUpDownTouch = true;
                    } else {
                        isUpDownTouch = false;
                    }
                }
                if(isDrag){
                    dealUpAndDown(chaY,chaX);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isLongClick = false;
                longClickHandler.removeCallbacksAndMessages(null);
                float chafX = event.getX() - mLastX;
                float chafY = event.getY() - mLastY;
                if (System.currentTimeMillis() - mStartClickDownTime < 400 && !isDrag) {//判断是否是点击事件
                    if (System.currentTimeMillis() - mLastClickDownTime > 100) {//防止快速点击
                        if( Math.abs(chafX) < 20 &&Math.abs(chafY) < 20){
                            onViewClick(event);
                        }

                    }
                } else if(isDrag){
                    if(chafY > 500){
                        onBackPressed();
                        Log.e(TAG, "onTouchEvent:   onBackPressed" );
                    }else {
                        returnNormal();
                    }
                }
                mLastClickDownTime = System.currentTimeMillis();
                break;
        }

        return super.onTouchEvent(event);
    }


    /**
     * 上下拖动处理
     */
    public void dealUpAndDown(float chaY,float chaX) {
        float v = -chaY / mScreenHeight;
        mLastPercentAlpha = 1 + v;
        mLastScale = 1 + v / 5;
        getContentView().setScaleX(mLastScale);
        getContentView().setScaleY(mLastScale);
        mLastPositionY = chaY;
        ObjectAnimator.ofFloat(getContentView(), "Y", 0, mLastPositionY).setDuration(0).start();


        float vX = -chaX / mScreenWidth;
        mLastPositionX = chaX;
        ObjectAnimator.ofFloat(getContentView(), "X", 0, mLastPositionX).setDuration(0).start();
    }

    /**
     * 恢复原来的界面
     */
    public void returnNormal() {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", mLastScale, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", mLastScale, 1.0f);
        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY", mLastRotateY, 0.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", Math.abs(mLastPercentAlpha), 1.0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("Y", mLastPositionY, 0.0f);
        PropertyValuesHolder mBottomPaint = PropertyValuesHolder.ofFloat("X", mLastPositionX, 0.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(getContentView(), scaleX, scaleY, translationY, rotationY,mBottomPaint);
        animator.setDuration(300);
        animator.start();
    }

    public  abstract void  onViewClick(MotionEvent event);
    public abstract void onViewLongClick(MotionEvent event);
    public abstract View getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (longClickHandler != null) {
            longClickHandler.removeCallbacksAndMessages(null);
        }
    }
}
