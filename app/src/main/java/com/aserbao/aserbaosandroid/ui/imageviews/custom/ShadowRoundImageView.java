package com.aserbao.aserbaosandroid.ui.imageviews.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.aserbao.aserbaosandroid.R;

public class ShadowRoundImageView extends AppCompatImageView {


    public ShadowRoundImageView(final Context context) {
        this(context, null);
    }

    public ShadowRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowRoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setScaleType(ScaleType.FIT_XY);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.ShadowRoundImageView, defStyle, 0);
        if (ta!=null){
            mRadius = ta.getDimension(R.styleable.ShadowRoundImageView_image_radius, 5);
            mShadowRadius = ta.getDimension(R.styleable.ShadowRoundImageView_image_shadow_radius, 5);
            mIsCircle = ta.getBoolean(R.styleable.ShadowRoundImageView_image_circle, true);
            mIsShadow = ta.getBoolean(R.styleable.ShadowRoundImageView_image_shadow, true);
            mShadowColor = ta.getInteger(R.styleable.ShadowRoundImageView_shadow_color,0xffe4e4e4);
            ta.recycle();
        }else {
            mRadius = 0;
            mShadowRadius = 0;
            mIsCircle = false;
            mIsShadow = false;
            mShadowColor = 0xffe4e4e4;
        }

    }


    private float mRadius;
    private float mShadowRadius;
    private int mShadowColor;
    private boolean mIsCircle;
    private boolean mIsShadow;
    private int width;
    private int height;
    private int imageWidth;
    private int imageHeight;
    private Paint mPaint;

    @Override
    public void onDraw(Canvas canvas) {
        width = canvas.getWidth() - getPaddingLeft() - getPaddingRight();//控件实际大小
        height = canvas.getHeight() - getPaddingTop() - getPaddingBottom();

        if (!mIsShadow)
            mShadowRadius = 0;

        imageWidth = width - (int) mShadowRadius * 2;
        imageHeight = height - (int) mShadowRadius * 2;

        Bitmap image = drawableToBitmap(getDrawable());
        Bitmap reSizeImage = reSizeImage(image, imageWidth, imageHeight);
        initPaint();


        if (mIsCircle) {
            canvas.drawBitmap(createCircleImage(reSizeImage),
                    getPaddingLeft(), getPaddingTop(), null);

        } else {
            canvas.drawBitmap(createRoundImage(reSizeImage),
                    getPaddingLeft(), getPaddingTop(), null);
        }
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    private Bitmap createRoundImage(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap can't be null");
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Bitmap targetBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBitmap);

        mPaint.setShader(bitmapShader);

        RectF rect = new RectF(0, 0, imageWidth, imageHeight);
        targetCanvas.drawRoundRect(rect, mRadius, mRadius, mPaint);

        if (mIsShadow){
            mPaint.setShader(null);
            mPaint.setColor(mShadowColor);
            mPaint.setShadowLayer(mShadowRadius, 1, 1, mShadowColor);
            Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(target);

            RectF rectF = new RectF(mShadowRadius, mShadowRadius, width - mShadowRadius, height - mShadowRadius);
            canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setShadowLayer(0, 0, 0, 0xffffff);
            canvas.drawBitmap(targetBitmap, mShadowRadius, mShadowRadius, mPaint);
            return target;
        }else {
            return targetBitmap;
        }

    }


    private Bitmap createCircleImage(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap can't be null");
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Bitmap targetBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBitmap);

        mPaint.setShader(bitmapShader);

        targetCanvas.drawCircle(imageWidth / 2, imageWidth / 2, Math.min(imageWidth, imageHeight) / 2,
                mPaint);

        if (mIsShadow){
            mPaint.setShader(null);
            mPaint.setColor(mShadowColor);
            mPaint.setShadowLayer(mShadowRadius, 1, 1, mShadowColor);
            Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(target);

            canvas.drawCircle(width / 2, height / 2, Math.min(imageWidth, imageHeight) / 2,
                    mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setShadowLayer(0, 0, 0, 0xffffff);
            canvas.drawBitmap(targetBitmap, mShadowRadius, mShadowRadius, mPaint);
            return target;
        }else {
            return targetBitmap;
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicHeight(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 重设Bitmap的宽高
     *
     * @param bitmap
     * @param newWidth
     * @param newHeight
     * @return
     */
    private Bitmap reSizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算出缩放比
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 矩阵缩放bitmap
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }


}
