package com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 8:52 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.simple_touch
 */
public class CustomImageView extends ImageView implements View.OnTouchListener {
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context,  @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    private static final String TAG = "CustomImageView";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\t被调用了");
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
