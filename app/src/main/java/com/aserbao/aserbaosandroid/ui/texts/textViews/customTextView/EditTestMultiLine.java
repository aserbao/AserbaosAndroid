package com.aserbao.aserbaosandroid.ui.texts.textViews.customTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.EditText;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/14
 * email: 1142803753@qq.com
 */
@SuppressLint("AppCompatCustomView")
public class EditTestMultiLine extends EditText {

    private int backColor;

    private Paint linePaint;
    private int paintColor;
    private int lineHeight = 100;
    private int count = 2;

    public EditTestMultiLine(Context context) {
        this(context,null);
    }

    public EditTestMultiLine(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EditTestMultiLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        backColor = Color.rgb(249, 246, 230);
        linePaint = new Paint();
        paintColor = Color.rgb(160, 155, 145);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                invalidate();
                Log.e(TAG, "onGlobalLayout: ");
            }
        });
    }

    private static final String TAG = "EditTestMultiLine";

    public void onDraw(Canvas canvas) {
        //画背景颜色
        canvas.drawColor(backColor);
        //获取行的高度
        lineHeight = getLineHeight();

//        count = getHeight() / lineHeight;
        //记录画到哪一行
        int nextLine = 0;
        //因为这个组件可能给它设置了边距，所以要先画第一条线
        nextLine = getCompoundPaddingTop() + lineHeight;
        //给画笔设置颜色
        linePaint.setColor(paintColor);
        canvas.drawLine(100, nextLine, getRight(), nextLine, linePaint);
        //画线
        int lineCount = getLineCount();
        for(int i = 0; i < lineCount; i++) {
//        for(int i = 0; i < count; i++) {
            nextLine += lineHeight;
            canvas.drawLine(getLeft(), nextLine, getRight(), nextLine, linePaint);
            canvas.save();
            Log.e(TAG, "onDraw: " + lineHeight + " nextLine " + nextLine +" linecount = "+  lineCount);
        }

        //画出光标，让他能够编辑
        super.onDraw(canvas);
    }


}
