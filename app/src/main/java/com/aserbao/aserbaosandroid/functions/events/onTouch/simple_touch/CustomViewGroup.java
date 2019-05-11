package com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 8:48 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch
 */
public class CustomViewGroup extends FrameLayout {
    public CustomViewGroup( @NonNull Context context) {
        super(context);
    }

    public CustomViewGroup( @NonNull Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup( @NonNull Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "CustomViewGroup";




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Log.e(TAG, "\tonInterceptTouchEvent: \t" + ev.getAction()+ "\treturn = "+b );
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\treturn = "+b );
        return b;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\treturn = "+b );
        return b;
    }


}
