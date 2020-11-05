package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import android.util.Log;

import androidx.annotation.NonNull;

import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.utils.SizeUtils;

import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.AlignType.ALIGN_BOTTOM;
import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.AlignType.ALIGN_CENTER;
import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.AlignType.ALIGN_TOP;
import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.UNIT_PX;
import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.UNIT_SP;

/**
 * 注意：继承ReplacementSpan的文本EditText会把它当做一个整体，也就是一个ReplacementSpan就表示一个字符，删除时会直接全部删除
 */
public class CustomTextSpan extends ReplacementSpan {
    public static String TAG = "CustomTextSpan";
    /**
     * 绘制每一分段文本的画笔
     */
    private Paint mPaint;
    /**
     * 分段文本宽度
     */
    private int mWidth;
    /**
     * 分段文本左边距
     */
    private int mLeftMargin;
    /**
     * 分段文本对齐方式，默认底部对齐
     */
    @TypeConfig.AlignType
    private int mAlign;
    private static int SPACING;

    public CustomTextSpan(@NonNull Context context, @TypeConfig.Unit int unit, float textSize, Typeface typeFace, int color, int leftMarginDp, @TypeConfig.AlignType int align) {
        SPACING = SizeUtils.dp2px(context,2);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        switch (unit){
                case UNIT_PX:
                    mPaint.setTextSize(textSize);
                break;
                case UNIT_SP:
                    mPaint.setTextSize(SizeUtils.sp2px(context,textSize));
                break;
        }
        mPaint.setTypeface(typeFace);
        mPaint.setColor(color);
        mLeftMargin = SizeUtils.dp2px(context,leftMarginDp);
        mAlign = align;
    }

    /**
     *
     * @param paint: textView的画笔
     * @param text
     * @param start
     * @param end
     * @param fm
     * @return
     */
    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mWidth = (int) mPaint.measureText(text, start, end)+mLeftMargin;

        if(fm!=null){
            Paint.FontMetricsInt customTextPaintFm = mPaint.getFontMetricsInt();

            int top = customTextPaintFm.top-SPACING;
            int ascent = customTextPaintFm.ascent-SPACING;
            int bottom = customTextPaintFm.bottom+SPACING;
            int descent = customTextPaintFm.descent+SPACING;

            //每一分段文本的画笔不一样，字体大小，样式等不同导致不同分段文本高度不一致，
            //这里把每一分段的fm值赋为自定义画笔的fm值，这样分段所在行行文本的高度就等于该行所有分段高度最高的字体高度
            fm.top = top;
            fm.ascent = ascent;
            fm.bottom = bottom;
            fm.descent = descent;
        }
        return mWidth;
    }

    /**
     *
     * @param canvas
     * @param text
     * @param start
     * @param end
     * @param x
     * @param top：所在行的top
     * @param y
     * @param bottom：所在行的bottom
     * @param paint
     */
    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        switch (mAlign){
            case ALIGN_BOTTOM: {
                canvas.drawText(text, start, end, x + mLeftMargin, y, mPaint);
            }
            break;
            case ALIGN_CENTER: {
                Paint.FontMetricsInt customTextPaintFm = mPaint.getFontMetricsInt();
                Log.d(TAG, "draw: customTextPaintFm=" + customTextPaintFm.toString());
                //调整分段文本居中显示，这里的textHeight等于fm.descent-fm.ascent
                int textHeight = bottom - top;
                int segmentTextHeight = customTextPaintFm.bottom - customTextPaintFm.top;
                y = (textHeight - segmentTextHeight) / 2 - customTextPaintFm.top;
                canvas.drawText(text, start, end, x + mLeftMargin, y, mPaint);
            }
            break;
            case ALIGN_TOP:{
                Paint.FontMetricsInt customTextPaintFm = mPaint.getFontMetricsInt();
                Log.d(TAG, "draw: customTextPaintFm=" + customTextPaintFm.toString());
                //调整分段文本居中显示
                int textHeight = bottom - top;
                int segmentTextHeight = customTextPaintFm.bottom - customTextPaintFm.top;
                y -= (textHeight - segmentTextHeight) / 2;
                canvas.drawText(text, start, end, x + mLeftMargin, y, mPaint);
            }
            break;
            default: {
                canvas.drawText(text, start, end, x + mLeftMargin, y, mPaint);
            }
            break;
        }
    }
}
