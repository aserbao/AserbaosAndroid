package com.aserbao.aserbaosandroid.ui.texts.textViews.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.IntDef;

import com.aserbao.aserbaosandroid.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 竖排文字
 *
 */
public class VerticalTextView extends View {

    public static final int START_LEFT = 1;
    public static final int START_RIGHT = 2;

    public static final int NONE = 0;
    public static final int RIGHT_LINE = 2;
    public static final int LEFT_LINE = 1;


    @IntDef({START_LEFT, START_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface START_ORIENTATION {
    }

    @IntDef({NONE, RIGHT_LINE, LEFT_LINE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LINE_ORIENTATION {
    }

    private float textSize = 56;
    private int textColor = Color.BLACK;
    private String text = "";
    private int startOrientation = START_LEFT;
    private int lineOrientation = NONE;
    private float lineWidth = dip2px(getContext(), 0.5f);
    private int lineColor = Color.BLACK;
    private String cutChars;
    private float textHorizontalMargin = dip2px(getContext(), 4);
    private float textVerticalMargin = dip2px(getContext(), 3);
    private float line2TextMargin = -1;

    Paint paint;
    int width;
    int height = -1;

    public VerticalTextView(Context context) {
        super(context);
        init();
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalTextView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.VerticalTextView_v_start) {
                startOrientation = typedArray.getInt(index, START_LEFT);
            } else if (index == R.styleable.VerticalTextView_v_text) {
                text = typedArray.getString(index);
            } else if (index == R.styleable.VerticalTextView_v_textColor) {
                textColor = typedArray.getColor(index, Color.BLACK);
            } else if (index == R.styleable.VerticalTextView_v_textSize) {
                textSize = typedArray.getDimension(index, 16);
            } else if (index == R.styleable.VerticalTextView_v_cutChars) {
                cutChars = typedArray.getString(index);
            } else if (index == R.styleable.VerticalTextView_v_textVerticalMargin) {
                textVerticalMargin = typedArray.getDimension(index, textVerticalMargin);
            } else if (index == R.styleable.VerticalTextView_v_textHorizontalMargin) {
                textHorizontalMargin = typedArray.getDimension(index, textHorizontalMargin);
            } else if (index == R.styleable.VerticalTextView_v_line) {
                lineOrientation = typedArray.getInt(index, NONE);
            } else if (index == R.styleable.VerticalTextView_v_lineWidth) {
                lineWidth = typedArray.getDimension(index, lineWidth);
            } else if (index == R.styleable.VerticalTextView_v_lineColor) {
                lineColor = typedArray.getColor(index, Color.BLACK);
            } else if (index == R.styleable.VerticalTextView_v_line2TextMargin) {
                line2TextMargin = textHorizontalMargin / 2 + lineWidth / 2 - typedArray.getDimension(index, 0);
            }
        }
        init();
    }

    private void init() {
        paint = new Paint();
        if (textSize > 0) {
            paint.setTextSize(textSize);
        }
        paint.setColor(textColor);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = measureHeight(heightMeasureSpec);
        //此处修复relativelayout下存在的异常
        if (height == -1) {
            height = h;
        } else {
            if (height > h) {
                height = h;
            }
        }
        width = measureWidth(widthMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void setLine2TextMargin(float line2TextMargin) {

        this.line2TextMargin = textHorizontalMargin / 2 + lineWidth / 2 - line2TextMargin;
        invalidate();
    }

    public void setStartOrientation(@LINE_ORIENTATION int startOrientation) {
        this.startOrientation = startOrientation;
        invalidate();
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        invalidate();
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
        invalidate();
    }

    public void setTextHorizontalMargin(float textHorizontalMargin) {
        this.textHorizontalMargin = textHorizontalMargin;
        invalidate();
    }

    public void setTextVerticalMargin(float textVerticalMargin) {
        this.textVerticalMargin = textVerticalMargin;
        invalidate();
    }

    public void setLineOrientation(@LINE_ORIENTATION int lineOrientation) {
        this.lineOrientation = lineOrientation;
        invalidate();
    }

    public void setCutChars(String cutChars) {
        this.cutChars = cutChars;
        invalidate();
    }

    /**
     * 设置文字尺寸
     *
     * @param textSize
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    /**
     * 设置文字颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    /**
     * 设置文字起始方向
     *
     * @param startOrientation
     */
    public void setStart(@START_ORIENTATION int startOrientation) {
        this.startOrientation = startOrientation;
        invalidate();
    }


    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {

            result = specSize;
        } else {
            return (int) measureTextWidth();
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (getOneWordHeight() * text.length());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private float measureTextWidth() {
        if (getColNum() == 1) {
            return getOneWordWidth() + getPaddingLeft() + getPaddingRight();
        }

        return getOneWordWidth() * getColNum() + getPaddingLeft() + getPaddingRight();
    }

    private float getTextBaseLine(RectF rect) {
        Paint.FontMetricsInt metricsInt = paint.getFontMetricsInt();
        return (rect.top + rect.bottom - metricsInt.top - metricsInt.bottom) / 2;
    }


    private int getColNum() {

        int oneRowWordCount = getColWordCount();
        int colNum = 0;
        if (cutChars != null) {
            String[] textArray = text.split(cutChars);
            for (int i = 0; i < textArray.length; i++) {
                if (textArray[i].length() > oneRowWordCount) {
                    colNum += textArray[i].length() / oneRowWordCount;
                    if (textArray[i].length() % oneRowWordCount > 0) {
                        colNum++;
                    }
                } else {
                    colNum++;
                }
            }
        } else {
            colNum = text.length() / oneRowWordCount;
            if (text.length() % oneRowWordCount > 0) {
                colNum++;
            }
        }


        return colNum;
    }

    private float getOneWordWidth() {
        return paint.measureText("我") + textHorizontalMargin;
    }

    private float getOneWordHeight() {
       /* Paint.FontMetricsInt metricsInt = paint.getFontMetricsInt();
        return (metricsInt.bottom - metricsInt.top);*/
        Rect rect = new Rect();
        paint.getTextBounds("我", 0, 1, rect);
        return rect.height() + textVerticalMargin;
    }

    private int getColWordCount() {
        int oneLineWordCount = (int) (height / getOneWordHeight());
        return oneLineWordCount;
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int oneLineWordCount = getColWordCount();

        float w = getOneWordWidth();
        float h = getOneWordHeight();

        int colNum = getColNum();

        String[] cutCharArray = cutChars == null ? null : cutChars.split("|");
        if (cutCharArray != null) {
            String[] textArray = text.split(cutChars);
            int stepCol = 0;
            for (int n = 0; n < textArray.length; n++) {
                String text = textArray[n];
                int currentCol = 0;
                for (int i = 0; i < text.length(); i++) {
                    String str = String.valueOf(text.charAt(i));
                    int currentRow = i % oneLineWordCount;
                    if (colNum == 1) {
                        currentRow = i;
                    }
                    if (colNum > 1) {
                        currentCol = stepCol + (i / oneLineWordCount);
                    }
                    drawText(w, h, currentCol, currentRow, str, canvas);
                    if (i + 1 == text.length()) {
                        stepCol = currentCol + 1;
                    }
                }
            }
        } else {
            int currentCol = 0;
            for (int i = 0; i < text.length(); i++) {
                String str = String.valueOf(text.charAt(i));
                int currentRow = i % oneLineWordCount;
                if (colNum == 1) {
                    currentRow = i;
                }
                if (colNum > 1) {
                    currentCol = (i) / oneLineWordCount;
                }
                drawText(w, h, currentCol, currentRow, str, canvas);

            }
        }

    }

    private void drawText(float w, float h, int currentCol, int currentRow, String str, Canvas canvas) {
        RectF rectF;
        if (startOrientation == START_LEFT) {
            rectF = new RectF(currentCol * w, currentRow * h, currentCol * w + w, currentRow * h + h);
        } else {
            rectF = new RectF((width - (currentCol + 1) * w), currentRow * h, (width - (currentCol + 1) * w) + w, currentRow * h + h);
        }
        float baseline = getTextBaseLine(rectF);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(str, rectF.centerX(), baseline, paint);
        paint.setColor(lineColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        if (line2TextMargin == -1) {
            line2TextMargin = lineWidth * 1f / 2;
        }
        if (lineOrientation == RIGHT_LINE) {
            Path path = new Path();
            path.moveTo(rectF.right - line2TextMargin, rectF.top);
            path.lineTo(rectF.right - line2TextMargin, rectF.bottom);
            canvas.drawPath(path, paint);
        } else if (lineOrientation == LEFT_LINE) {
            Path path = new Path();
            path.moveTo(rectF.left + line2TextMargin, rectF.top);
            path.lineTo(rectF.left + line2TextMargin, rectF.bottom);
            canvas.drawPath(path, paint);
        }
    }
}