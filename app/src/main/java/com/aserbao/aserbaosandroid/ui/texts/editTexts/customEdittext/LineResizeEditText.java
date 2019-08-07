package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;

import com.aserbao.aserbaosandroid.R;

import java.util.HashMap;
import java.util.Map;

public class LineResizeEditText extends AppCompatEditText {
    private static final String TAG = "LineResizeEditText";

    private int mMaxTextSize = 40;
    private int mMinTextSize = 12;
    private int mWidthPx;

    private TextPaint mPaint;
    private boolean mInitialized = false;

    private boolean mResizing = false;

    private Handler mResizingHandler;

    private final Map<String, Integer> mCachedSizes = new HashMap<>();

    public LineResizeEditText(Context context) {
        this(context, null);
    }

    public LineResizeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineResizeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMaxTextSize = context.getResources().getDimensionPixelSize(R.dimen.creator_edit_text_max_textSize);
        mMinTextSize = context.getResources().getDimensionPixelSize(R.dimen.creator_edit_text_min_textSize);
        mWidthPx = context.getResources().getDimensionPixelSize(R.dimen.creator_edit_text_width);

        mResizingHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                adjustTextSize();
            }
        };

        mInitialized = true;
    }

    @Override
    public void setTypeface(final Typeface tf) {
        if (mPaint == null) {
            mPaint = new TextPaint(getPaint());
        }

        mPaint.setTypeface(tf);
        super.setTypeface(tf);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (mResizing) {
            mResizing = false;
            return;
        }

        adjustTextSize();
    }

    private void adjustTextSize() {
        if (!mInitialized) {
            return;
        }

        int measuredWidth = getMeasuredWidth();
        if (measuredWidth <= 0) {
            return;
        }

        int availableWidth = measuredWidth - getCompoundPaddingLeft() - getCompoundPaddingRight();
        Editable text = getText();
        int selectionEnd = getSelectionEnd();
        String textStr = text.toString();
        TextPaint textPaint = new TextPaint(mPaint);
        textPaint.setTextSize(mMinTextSize);
        Layout layout = new StaticLayout(textStr, textPaint, availableWidth, Layout.Alignment.ALIGN_NORMAL,
                1 /* spacingMult */, 0 /* spacingAdd */, true /*includePad */);

        int lineCount = layout.getLineCount();

        for (int i=0; i<lineCount; i++) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            String subText = textStr.substring(lineStart, lineEnd);
            Integer integer = mCachedSizes.get(subText);
            int lineTextSize = integer == null ? 0 : integer;
            if (lineTextSize != 0) {
            } else {
                lineTextSize = binarySearchSize(textPaint, i, subText, availableWidth, mMinTextSize, mMaxTextSize);
                mCachedSizes.put(subText, lineTextSize);
            }

            text.setSpan(new AbsoluteSizeSpan(lineTextSize), lineStart, lineEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        mResizing = true;
        setText(text);
        setSelection(selectionEnd);
    }

    public void setHint(String text) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(mMinTextSize);

        Layout layout = new StaticLayout(text, textPaint, mWidthPx, Layout.Alignment.ALIGN_NORMAL,
                1 /* spacingMult */, 0 /* spacingAdd */, true /*includePad */);
        int lineCount = layout.getLineCount();

        SpannableString sp = new SpannableString(text);
        for (int i=0; i<lineCount; i++) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            String subText = text.substring(lineStart, lineEnd);
            int lineTextSize = binarySearchSize(textPaint, i, subText, mWidthPx, mMinTextSize, mMaxTextSize);
            sp.setSpan(new AbsoluteSizeSpan(lineTextSize), lineStart, lineEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        setHint(sp);

    }

    private static int binarySearchSize(TextPaint textPaint, int linePosition, String text, int width, int minSize, int maxSize){
        int lowSize = minSize;
        int highSize = maxSize;
        int currentSize = lowSize + (int) Math.floor((highSize - lowSize) / 2f);
        while (lowSize < currentSize) {
            if (hasLineBreak(textPaint, linePosition, text, currentSize, width)) {
                highSize = currentSize;
            } else {
                lowSize = currentSize;
            }
            currentSize = lowSize + (int) Math.floor((highSize - lowSize) / 2f);
        }

        return currentSize;
    }

    private Matrix matrix;
    public void setMatrix(float fl){
        matrix= new Matrix();
        matrix.postScale(fl,fl);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (matrix != null){
            canvas.setMatrix(matrix);
        }
        super.onDraw(canvas);
    }

    private static boolean hasLineBreak(TextPaint textPaint, int linePosition, String text, int currentSize, int width) {
        textPaint.setTextSize(currentSize);
        float measureWidth = textPaint.measureText(text);
        //Log.d(TAG, "" + "Line " + linePosition + ", text==" + text + ", textSize==" + currentSize + "measureWidth==" + measureWidth + ", width==" + width);
        return measureWidth > width;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mResizingHandler != null) {
            mResizingHandler.removeMessages(0);
        }
    }
}
