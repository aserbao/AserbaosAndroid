package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
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
    public CustomItemRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
}
