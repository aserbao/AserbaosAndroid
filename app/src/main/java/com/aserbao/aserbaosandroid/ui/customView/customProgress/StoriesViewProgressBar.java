package com.aserbao.aserbaosandroid.ui.customView.customProgress;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;

public class StoriesViewProgressBar extends View{
    private int mProgressCount = 1;
    private int mMaxProgressCount = 50;
    private int mScreenWidth = 0;

    public StoriesViewProgressBar(Context context) {
        this(context,null);
    }

    public StoriesViewProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StoriesViewProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setProgressCount(int count){
        if(count > 1) {
            mProgressCount = count;
        }
    }

    private void init() {
        mScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int dp2 = DisplayUtil.dipToPX(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
