package com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 8:54 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch
 */
public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private static final String TAG = "CustomButton";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\t被调用了");
//        return false;
        boolean b = super.dispatchTouchEvent(ev);
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\treturn = "+b );
        return b;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\t被调用了");
        return true;
        /*boolean b = super.onTouchEvent(event);
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\treturn = "+b );
        return b;*/
    }

}
