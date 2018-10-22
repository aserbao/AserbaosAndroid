package com.aserbao.aserbaosandroid.AudioAndVideo.media.exoplayer.playerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.exoplayer2.ui.PlayerView;

public class MyPlayerView extends PlayerView
{
    private static final String TAG = "MyPlayerView";
    public MyPlayerView(Context context) {
        super(context);
    }

    public MyPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e(TAG, "MyPlayerView: " + b );
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean b = super.onInterceptTouchEvent(ev);
        Log.e(TAG, "onInterceptTouchEvent: " + b );
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        Log.e(TAG, "onTouchEvent: " + b );
        return b;
    }
}
