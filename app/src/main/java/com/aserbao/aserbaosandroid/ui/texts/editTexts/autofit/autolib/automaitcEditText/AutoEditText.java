package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * https://blog.csdn.net/aerfa789/article/details/105497393
 * 仿Instragram 的自动排版输入框,文本每行的字体大小可能不一样
 */
public class AutoEditText extends AppCompatEditText {
    private static final String TAG = "AutoEditText";
    private AutoProcessor mAutoMaticProcessor;

    public AutoEditText(Context context) {
        this(context,null);
    }

    public AutoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mAutoMaticProcessor = new AutoProcessor(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
       mAutoMaticProcessor.handleSizeChanged(w,h,oldw,oldh);
    }
}
