package com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class ResizeLayout extends RelativeLayout {
    private static final String TAG = "ResizeLayout";
    private static int count = 0;

    public ResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.e(TAG + count++, "=>onResize called! w=" + w + ",h=" + h + ",oldw=" + oldw + ",oldh=" + oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG+ count++, "=>OnLayout called! l=" + l + ", t=" + t + ",r=" + r + ",b=" + b + "isChanged" + changed);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.e(TAG + count++, "=>onMeasure called! widthMeasureSpec=" + widthMeasureSpec + ", heightMeasureSpec=" + heightMeasureSpec);
    }
}