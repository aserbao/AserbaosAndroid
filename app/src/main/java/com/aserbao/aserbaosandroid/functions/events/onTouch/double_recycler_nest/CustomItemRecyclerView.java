package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 5:06 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest
 */
public class CustomItemRecyclerView extends RecyclerView {
    private static final String TAG = "CustomItemRecyclerView";


    public CustomItemRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private float mStartRawY;
    private  boolean mIsTop = false;

    public void setmIsTop(boolean mIsTop) {
        Log.e(TAG, "setmIsTop: " + mIsTop );
        this.mIsTop = mIsTop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartRawY = e.getRawY();
                Log.e(TAG, "onTouchEvent: ACTION_DOWN = " + mStartRawY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsTop){
                    float chaY = e.getRawY() - mStartRawY;
                    if (chaY > 0){
                        Log.e(TAG, "onTouchEvent: ACTION_MOVE 向下滑 距离为 = "+ chaY + "\t"+ mIsTop + "\t"+ mStartRawY);
                        return true;
                    }
                    Log.e(TAG, "onTouchEvent: ACTION_MOVE "+ chaY + "\t"+ mIsTop + "\t"+ mStartRawY);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(e);
    }
}
