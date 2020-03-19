package com.aserbao.aserbaosandroid.functions.events.demo.rvAndButton;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-18 17:55
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.demo.rvAndButton
 */
public class ADispatchTouchEventFragment extends FrameLayout {

    private long mStartTime;

    public ADispatchTouchEventFragment(@NonNull Context context) {
        this(context,null);
    }

    public ADispatchTouchEventFragment(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ADispatchTouchEventFragment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ADispatchTouchEventFragment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private static final String TAG = "ADispatchTouchEventFrag";

    private float mStartX,mStartY;
    private boolean mIsInsideView = false;
    private boolean mIsLongClick = false;
    private boolean mIsScroller = false;


    public void setmIsInsideView(boolean mIsInsideView) {
        this.mIsInsideView = mIsInsideView;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                mStartTime = System.currentTimeMillis();
            case MotionEvent.ACTION_MOVE:
                float difX = event.getX() - mStartX;
                float difY = event.getY() - mStartY;
                float absDifX = Math.abs(difX);
                float absDifY = Math.abs(difY);
                if (mIsInsideView && mIScrollListener != null){
                    if (!mIsLongClick  && !mIsScroller && absDifX + absDifY < 20 && System.currentTimeMillis() - mStartTime > 150){
                        mIsLongClick = true;
                    }
                    if (mIsLongClick){
                        mIScrollListener.moveListener(difX,difY);
                    }
                    if (!mIsScroller  && !mIsLongClick && absDifX > absDifY && absDifX + absDifY > 50){
                        mIsScroller = true;
                    }
                    if (mIsScroller){
                        mIScrollListener.scrollListener(difX);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!mIsLongClick  && !mIsScroller && mIsInsideView && System.currentTimeMillis() - mStartTime < 150){
                    mIScrollListener.clickListener();
                }
                mIsLongClick = false;
                mIsScroller = false;
                mIsInsideView = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private  IScrollListener mIScrollListener;

    public void setmIScrollListener(IScrollListener mIScrollListener) {
        this.mIScrollListener = mIScrollListener;
    }

    public interface IScrollListener{
        void scrollListener(float scrollX);
        void moveListener(float moveX,float moveY);
        void clickListener();
    }
}
