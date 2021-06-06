package com.aserbao.aserbaosandroid.ui.customView.selector;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.aserbao.aserbaosandroid.R;

/**
* @Created time:2021/6/3 6:12 PM
* @author: aserbao
* @description: 圆角矩形 带选择框的自定义视图
 *  BorderSelRoundImageView borderSelRoundImageView3 = new BorderSelRoundImageView(context);
 *         borderSelRoundImageView3.isHasBorder = true;
 *         borderSelRoundImageView3.isChecked = true;
 *         borderSelRoundImageView3.currMode = 2;
 *         borderSelRoundImageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
 *         borderSelRoundImageView3.setImageDrawable(getResources().getDrawable(R.drawable.mm_2));
 *         LayoutParams params3 = new LayoutParams(dp100, dp100);
 *         addView(borderSelRoundImageView3,getChildCount(),params3);
**/
public class BorderSelRoundImageView extends AppCompatImageView {
    /**
     * 圆形模式
     */
    private static final int MODE_CIRCLE = 1;
    /**
     * 普通模式
     */
    private static final int MODE_NONE = 0;
    /**
     * 圆角模式
     */
    private static final int MODE_ROUND = 2;
    private Paint mPaint;
    public int currMode = MODE_CIRCLE;
    /**
     * 圆角半径
     */
    private int currRadius = dp2px(10);

    /**
     * 是否有外边框
     */
    public boolean isHasBorder = true;
    /**
     * 是否是选中态？
     */
    public boolean isChecked = false;
    /**
     * 外边框颜色
     */
    public int borderColor = Color.WHITE;
    /**
     * 中心区域填充颜色
     */
    public int centerColor = Color.RED;
    /**
     * 外边框线宽
     */
    public int borderWidth = dp2px(2);
    /**
     * 外边框距离中心的距离
     */
    private int borderInterval = dp2px(4);



    private int mViewWidth, mViewHeight,centerWidth,centerHeight;
    private Paint mBorderPaint,mCenterPaint;


    public BorderSelRoundImageView(Context context) {
        super(context);
        initViews();
    }

    public BorderSelRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderSelRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(context, attrs, defStyleAttr);
        initViews();
    }

    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BorderSelRoundImageView, defStyleAttr, 0);
        currMode = a.hasValue(R.styleable.BorderSelRoundImageView_sel_type) ? a.getInt(R.styleable.BorderSelRoundImageView_sel_type, MODE_CIRCLE) : MODE_CIRCLE;
        currRadius = a.hasValue(R.styleable.BorderSelRoundImageView_sel_radius) ? a.getDimensionPixelSize(R.styleable.BorderSelRoundImageView_sel_radius, currRadius) : currRadius;
        borderColor = a.hasValue(R.styleable.BorderSelRoundImageView_sel_border_color) ? a.getColor(R.styleable.BorderSelRoundImageView_sel_border_color, borderColor) : borderColor;
        centerColor = a.hasValue(R.styleable.BorderSelRoundImageView_sel_center_color) ? a.getColor(R.styleable.BorderSelRoundImageView_sel_center_color, centerColor) : centerColor;
        borderWidth = a.hasValue(R.styleable.BorderSelRoundImageView_sel_border_width) ? a.getDimensionPixelSize(R.styleable.BorderSelRoundImageView_sel_border_width, borderWidth) : borderWidth;
        borderInterval = a.hasValue(R.styleable.BorderSelRoundImageView_sel_border_interval) ? a.getDimensionPixelSize(R.styleable.BorderSelRoundImageView_sel_border_interval, borderInterval) : borderInterval;
        a.recycle();
    }


    private void initViews() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 当模式为圆形模式的时候，我们强制让宽高一致
         */
        if (currMode == MODE_CIRCLE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int result = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(result, result);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        if(isHasBorder){
            centerWidth =  mViewWidth - (borderInterval + borderWidth) * 2;
            centerHeight = mViewHeight - (borderInterval + borderWidth) * 2;
        }else{
            centerWidth = mViewWidth;
            centerHeight = mViewHeight;
        }
        if (mBorderPaint == null) {
            mBorderPaint = new Paint();
            mBorderPaint.setAntiAlias(true);
            mBorderPaint.setStyle(Paint.Style.STROKE);
        }
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStrokeWidth(borderWidth);

        Drawable mDrawable = getDrawable();
        if (mDrawable == null) {
            drawSolidColor(canvas);
        }else{
            drawDrawable(canvas);
        }
    }

    /**
     * drawable转换成bitmap
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //根据传递的scaletype获取matrix对象，设置给bitmap
        Matrix matrix = getImageMatrix();
        if (matrix != null) {
            canvas.concat(matrix);
        }
        drawable.draw(canvas);
        return bitmap;
    }


    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }



    /**
     * todo:没有添加padding的计算
     * 绘制没有图片的情况,纯色
     * @param canvas
     */
    private void drawSolidColor(Canvas canvas){
        if (mCenterPaint == null) {
            mCenterPaint = new Paint();
            mCenterPaint.setAntiAlias(true);
            mCenterPaint.setStyle(Paint.Style.FILL);
        }
        mCenterPaint.setColor(centerColor);
        final int saveCount = canvas.getSaveCount();
        canvas.save();
        if (currMode == MODE_CIRCLE) {//当为圆形模式的时候
            if(isHasBorder){
                if(isChecked){
                    mBorderPaint.setColor(borderColor);
                    canvas.drawCircle(mViewWidth /2, mViewHeight /2, mViewWidth /2 - borderWidth,mBorderPaint);
                }
            }
            canvas.drawCircle(mViewWidth /2, mViewHeight /2,Math.min(centerHeight,centerWidth)/2,mCenterPaint);
        } else if (currMode == MODE_ROUND) {//当为圆角模式的时候
            if(isHasBorder){
                if(isChecked){
                    int padding = dp2px(2.5f);
                    RectF rectF = new RectF(padding, padding, mViewWidth-padding, mViewHeight - padding);
                    canvas.drawRoundRect(rectF,currRadius , currRadius,mBorderPaint);
                }
                int left = borderInterval;
                RectF centerRectF = new RectF(left, left, mViewWidth - left, mViewHeight - left);
                int ry = currRadius / 3 * 2;
                canvas.drawRoundRect(centerRectF, ry, ry,mCenterPaint);
            }
        }else{
            setBackgroundColor(centerColor);
        }
        canvas.restoreToCount(saveCount);
    }

    /**
     * 绘制中心是图片的情况
     * @param canvas
     */
    private void drawDrawable(Canvas canvas){
        Drawable mDrawable = getDrawable();
        Matrix mDrawMatrix = getImageMatrix();
        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }

        if (mDrawMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
            mDrawable.draw(canvas);
        } else {
            final int saveCount = canvas.getSaveCount();
            canvas.save();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (getCropToPadding()) {
                    final int scrollX = getScrollX();
                    final int scrollY = getScrollY();
                    canvas.clipRect(scrollX + getPaddingLeft(), scrollY + getPaddingTop(),
                        scrollX + getRight() - getLeft() - getPaddingRight(),
                        scrollY + getBottom() - getTop() - getPaddingBottom());
                }
            }
            canvas.translate(getPaddingLeft(), getPaddingTop());
            if (currMode == MODE_CIRCLE) {//当为圆形模式的时候
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                if(isHasBorder){
                    if(isChecked){
                        mBorderPaint.setColor(borderColor);
                        canvas.drawCircle(mViewWidth /2, mViewHeight /2, mViewWidth /2 - borderWidth,mBorderPaint);
                    }
                    canvas.drawCircle(mViewWidth /2, mViewHeight /2, mViewWidth /2 - borderWidth, mPaint);
                }else{
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
                }
            } else if (currMode == MODE_ROUND) {//当为圆角模式的时候
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
                if(isHasBorder){
                    int padding = borderWidth;
                    if(isChecked){
                        RectF rectF = new RectF(padding, padding, mViewWidth-padding, mViewHeight - padding);
                        canvas.drawRoundRect(rectF,currRadius , currRadius,mBorderPaint);
                    }
                    int left = borderInterval + borderWidth;

                    RectF centerRectF = new RectF(left, left, mViewWidth - left, mViewHeight - left);
                    int ry = currRadius / 3 * 2;
                    canvas.drawRoundRect(centerRectF, ry,ry, mPaint);
                }else{
                    canvas.drawRoundRect(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom()),
                        currRadius, currRadius, mPaint);
                }
            } else {
                if (mDrawMatrix != null) {
                    canvas.concat(mDrawMatrix);
                }
//                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mDrawable.draw(canvas);
            }
            canvas.restoreToCount(saveCount);
        }
    }


    public void setCenterColor(int centerColor) {
        this.centerColor = centerColor;
        if (mCenterPaint == null) {
            mCenterPaint = new Paint();
            mCenterPaint.setAntiAlias(true);
            mCenterPaint.setStyle(Paint.Style.FILL);
        }
        mCenterPaint.setColor(centerColor);
        invalidate();
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        invalidate();
    }

    public void setBorderInterval(int borderInterval) {
        this.borderInterval = borderInterval;
        invalidate();
    }

    public void setCurrMode(int currMode) {
        this.currMode = currMode;
        invalidate();
    }
}
