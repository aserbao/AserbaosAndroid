package com.aserbao.aserbaosandroid.ui.customView.smallView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 手指拖动移动的View
 */
public class OnTouchMoveView extends Button {

    private float mRawX;
    private float mRawY;

    public OnTouchMoveView(Context context) {
        this(context,null);
    }

    public OnTouchMoveView(Context context,  @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OnTouchMoveView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public OnTouchMoveView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static final String TAG = "OnTouchMoveView";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRawX = event.getRawX();
                mRawY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(event.getRawX()-mRawX);
                setTranslationY(event.getRawY()-mRawY);
                Log.e(TAG, "onTouchEvent: \ngetX = " + getX() + " getY = "+ getY() + " getRawX = " + event.getRawX() + " getRawY= " + event.getRawY() + " \n  mRawX =" + mRawX + " mRawY =" +mRawY);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }


}
