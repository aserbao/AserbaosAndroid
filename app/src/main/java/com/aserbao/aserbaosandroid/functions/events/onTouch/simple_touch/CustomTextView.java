package com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 9:02 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "CustomTextView";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\t被调用了");
//        return true;
        boolean b = super.dispatchTouchEvent(ev);
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\treturn = "+b );
        return b;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\t被调用了");
        boolean b = super.onTouchEvent(event);
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\treturn = "+b );
        return b;
    }
}
