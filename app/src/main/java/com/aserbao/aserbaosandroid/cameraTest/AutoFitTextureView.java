package com.aserbao.aserbaosandroid.cameraTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by aserbao on 2018 2018/1/11.22:28
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class AutoFitTextureView extends TextureView {
    private float mRatioWH = 9f/16f;

    public AutoFitTextureView(Context context) {
        super(context);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectRatio(float ratioWH)
    {
        mRatioWH = ratioWH;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width < height * mRatioWH)
        {
            setMeasuredDimension(width, (int)(width * mRatioWH));
        }
        else
        {
            setMeasuredDimension((int)(height * mRatioWH), height);
        }
    }
}
