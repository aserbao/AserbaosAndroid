package com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

public class LinearColorPicker extends View {
   /**
     * 滑块
     */
    private Drawable mThumb;
    /**
     * 滑动的距离
     */
    private float mThumbOffset = 0;
    private int mPaddingLeft;
    private int mPaddingTop;
    /**
     * 画笔
     */
    private Paint mPaint = null;
    /**
     * 方向
     */
    private int mOrientation;
    /**
     * 颜色条宽度
     */
    private float mColorPanelWidth;
    /**
     * 颜色数组
     */
    private int[] mColors = new int[]{ // 渐变色数组
            0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};

    private static final int MAX_PROGRESS = 100;
    /**
     * 需要减去的偏移量 为滑块一半
     */
    private float mOffset = 0f;

    /**
     * 默认高度
     */
    private int mDefaultHeight = 50;

    /**
     *
     */
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 0;

    public LinearColorPicker(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public LinearColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public LinearColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {

        if (null == attrs)
            return;
        setClickable(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.custom);
        // 滑块
        mThumb = a.getDrawable(R.styleable.custom_thumbDrawable);
        // 方向
        mOrientation = a.getInt(R.styleable.custom_colorOrientation, HORIZONTAL);
        // 颜色条宽度
        mColorPanelWidth = a.getDimensionPixelOffset(R.styleable.custom_colorPanelWidth, mDefaultHeight);
        // 颜色数组
        int colorArrayId = a.getResourceId(R.styleable.custom_gradientArray, -1);
        if (-1 != colorArrayId) {
            mColors = getResources().getIntArray(colorArrayId);
        }
        //释放资源
        a.recycle();

        if (null != mThumb) {
            mThumb.setBounds(0, 0, mThumb.getIntrinsicWidth(), mThumb.getIntrinsicWidth());
        }
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();

        //初始偏移量
        mOffset = null == mThumb ? 0 : mThumb.getIntrinsicWidth() / 2;
        lastX = mOffset;
        lastY = mOffset;

        //画笔初始化
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mColorPanelWidth);
    }

    public Drawable getThumb() {
        return mThumb;
    }

    public void setThumb(Drawable thumb) {
        this.mThumb = thumb;
        requestLayout();
    }

    public int getOrientation() {
        return mOrientation;
    }

    /**
     * 颜色条方向 {@link #HORIZONTAL} or {@link #VERTICAL}. Default value is
     * {@link #HORIZONTAL}.
     *
     * @param orientation
     */
    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
        requestLayout();
    }

    public float getColorPanelWidth() {
        return mColorPanelWidth;
    }

    public void setColorPanelWidth(float panelWidth) {
        this.mColorPanelWidth = panelWidth;
        if (null == mPaint)
            return;
        mPaint.setStrokeWidth(mColorPanelWidth);
        requestLayout();
    }

    public int[] getColors() {
        return mColors;
    }

    public void setColors(int... colors) {
        this.mColors = colors;
        requestLayout();
    }

    public void setColors(List<Integer> colors) {
        if (null == colors || 0 == colors.size())
            return;
        mColors = new int[colors.size()];
        for (int i = 0, count = colors.size(); i < count; i++) {
            mColors[i] = colors.get(i);
        }
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //设置渐变画笔渲染
        if (null == mPaint.getShader()) {
            Shader s = isHorizontal()
                    ? new LinearGradient(mOffset, 0, getMeasuredWidth() - mOffset, 0, mColors, null, Shader.TileMode.CLAMP)
                    : new LinearGradient(0, mOffset, 0, getMeasuredHeight() - mOffset, mColors, null, Shader.TileMode.CLAMP);
            mPaint.setShader(s);
        }
        //画渐变颜色条
        if (isHorizontal()) {
            canvas.drawLine(mOffset, getMeasuredHeight() / 2, getMeasuredWidth() - mOffset, getMeasuredHeight() / 2, mPaint);
        } else {
            canvas.drawLine(getMeasuredWidth() / 2, mOffset, getMeasuredWidth() / 2, getMeasuredHeight() - mOffset, mPaint);
        }
        //画滑块
        if (mThumb != null) {
            canvas.save();

            if (isHorizontal()) {
                canvas.translate(mPaddingLeft + mThumbOffset, mPaddingTop);
            } else {
                canvas.translate(mPaddingLeft, mPaddingTop + mThumbOffset);
            }
            mThumb.draw(canvas);
            canvas.restore();
        }

        //回调当前颜色
        measureProgress();
        if (null != mSelectListener) {
            mSelectListener.onColorSelect(getCurrentColor(isHorizontal() ? lastX : lastY), progress);
        }
    }

    /**
     * 设置进度值
     *
     * @param progress
     */
    public void setProgress(final int progress) {
        post(new Runnable() {
            @Override
            public void run() {
                if (isHorizontal()) {
                    mThumbOffset = (progress * (getMeasuredWidth() - 2 * mOffset)) / MAX_PROGRESS;
                    lastX = mThumbOffset + mOffset;
                } else {
                    mThumbOffset = (progress * (getMeasuredHeight() - 2 * mOffset)) / MAX_PROGRESS;
                    lastY = mThumbOffset + mOffset;
                }

                invalidate();
            }
        });
    }

    /**
     * 通过颜色设置进度条
     *
     * @param color
     */
    public void setProgressByColor(final int color) {
        post(new Runnable() {
            @Override
            public void run() {

                measureProgressFromColor(color);
            }
        });
    }

    /**
     * 通过颜色计算进度<br/>
     * TODO 暂时只想到这个比较笨的办法 希望有更好的方法
     *
     * @param color
     */
    private void measureProgressFromColor(int color) {
        int xCount = isHorizontal() ? getMeasuredWidth() : getMeasuredHeight();
        for (int colorPoint = 0; colorPoint < xCount - 2 * mOffset; colorPoint++) {
            int currentColor = getCurrentColor(colorPoint);
            int offset = Math.abs(currentColor - color);
            if (offset <= 260) {
                if (isHorizontal()) {
                    lastX = colorPoint;
                    mThumbOffset = lastX - mOffset;
                } else {
                    lastY = colorPoint;
                    mThumbOffset = lastY - mOffset;
                }
                measureProgress();
                setProgress(progress);
                break;
            }
        }

    }

    /**
     * 计算当前进度
     */
    private void measureProgress() {
        int lineWidth = isHorizontal() ? getMeasuredWidth() : getMeasuredHeight();
        progress = (int) ((mThumbOffset / (lineWidth - 2 * mOffset)) * MAX_PROGRESS);
    }

    /**
     * 获取当前进度值
     *
     * @return
     */
    public int getCurrentProgress() {

        return progress;
    }

    private float lastX = 0;
    private float lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                return isHorizontal() ? handleBoundHorizontal(event) : handleBoundVertical(event);
        }
        return super.onTouchEvent(event);
    }

    private int progress = -1;

    /**
     * 处理垂直方向边界值
     *
     * @param event 当前事件
     */
    private boolean handleBoundHorizontal(MotionEvent event) {
        mThumbOffset += (event.getX() - lastX);
        float colorMaxWidth = getMeasuredWidth() - 2 * mOffset;
        if (mThumbOffset <= 0) {
            mThumbOffset = 0;
            lastX = mOffset;
            invalidate();
            return true;
        }
        if (mThumbOffset > colorMaxWidth) {
            mThumbOffset = colorMaxWidth;
            lastX = getMeasuredWidth() - mOffset;
            invalidate();
            return true;
        }
        lastX = event.getX();
        invalidate();
        return true;
    }

    /**
     * 处理水平方向边界值
     *
     * @param event 当前事件
     */
    private boolean handleBoundVertical(MotionEvent event) {
        mThumbOffset += (event.getY() - lastY);
        if (mThumbOffset <= 0) {
            mThumbOffset = 0;
            lastY = mOffset;
            invalidate();
            return true;
        }
        if (mThumbOffset > getMeasuredHeight() - 2 * mOffset) {
            mThumbOffset = getMeasuredHeight() - 2 * mOffset;
            lastY = getMeasuredHeight() - mOffset;
            invalidate();
            return true;
        }
        lastY = event.getY();
        invalidate();
        return true;
    }

    private int ave(int startColor, int endColor, int unit, int step) {
        return startColor + (endColor - startColor) * step / unit;
    }

    /**
     * 获取当前颜色
     *
     * @return
     */
    private int getCurrentColor(float colorPoint) {
        int unit = (int) ((isHorizontal() ? getMeasuredWidth() - 2 * mOffset : getMeasuredHeight() - 2 * mOffset) / (mColors.length - 1));
        int position = (int) (colorPoint - mOffset);
        int i = position / unit;
        int step = position % unit;
        if (i >= mColors.length - 1)
            return mColors[mColors.length - 1];
        int c0 = mColors[i];
        int c1 = mColors[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), unit, step);
        int r = ave(Color.red(c0), Color.red(c1), unit, step);
        int g = ave(Color.green(c0), Color.green(c1), unit, step);
        int b = ave(Color.blue(c0), Color.blue(c1), unit, step);
        return Color.argb(a, r, g, b);
    }


    /**
     * 判断当前方向是不是水平
     *
     * @return
     */
    private boolean isHorizontal() {
        return HORIZONTAL == mOrientation;
    }

    private OnColorSelectListener mSelectListener;

    /**
     * 设置监听器
     *
     * @param mSelectListener
     */
    public void setOnColorSelectListener(OnColorSelectListener mSelectListener) {
        this.mSelectListener = mSelectListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 计算宽高
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 测量宽度
     *
     * @param measureSpec 模式
     * @return 宽度
     */
    private int measureWidth(int measureSpec) {

        int specSize = MeasureSpec.getSize(measureSpec);

        int width = null == mThumb ? mDefaultHeight : mThumb.getIntrinsicWidth();
        return isHorizontal() ? specSize : width + getPaddingLeft() + getPaddingRight();
    }

    /**
     * 测量高度
     *
     * @param measureSpec 模式
     * @return 高度
     */
    private int measureHeight(int measureSpec) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int width = null == mThumb ? mDefaultHeight : mThumb.getIntrinsicWidth();
        return isHorizontal() ? width + getPaddingTop() + getPaddingBottom() : specSize;
    }

    public interface OnColorSelectListener {
        void onColorSelect(int color, int progress);
    }
}
