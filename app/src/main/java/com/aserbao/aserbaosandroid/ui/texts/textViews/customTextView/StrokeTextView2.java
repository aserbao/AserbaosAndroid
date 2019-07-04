package com.aserbao.aserbaosandroid.ui.texts.textViews.customTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

/**
 * 文字描边的textView
 */
public class StrokeTextView2 extends AppCompatTextView {


    private TextPaint strokePaint;

    public StrokeTextView2(Context context) {
        this(context,null);
    }

    public StrokeTextView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StrokeTextView2(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
        TextPaint paint = getPaint();
        strokePaint = new TextPaint();
        strokePaint.setStrokeWidth(DisplayUtil.dip2px(5));                    //设置描边宽度
        strokePaint.setStyle(Paint.Style.STROKE);                             //对文字只描边
        strokePaint.setColor(Color.RED);
        strokePaint.setTextSize(paint.getTextSize());
        strokePaint.setTypeface(paint.getTypeface());
        strokePaint.setFlags(paint.getFlags());
        strokePaint.setAlpha(paint.getAlpha());
    }



    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        canvas.drawText(text,(getWidth() - strokePaint.measureText(text)) / 2,
            getBaseline(), strokePaint);
        super.onDraw(canvas);
    }
}
