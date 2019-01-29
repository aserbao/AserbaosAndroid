package com.aserbao.aserbaosandroid.ui.canvas.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/17 3:18 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.test
 */
public class MatrixView extends View {

    private Paint mPaint;
    private Matrix mMatrix;
    private Bitmap mOtherArrowBitmap;


    public MatrixView(Context context) {
        this(context,null);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mMatrix = new Matrix();
        mOtherArrowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_dancebase_other);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int arrowBitmapHeight = mOtherArrowBitmap.getHeight();
        int arrowBitmapWidth = mOtherArrowBitmap.getWidth();

        mMatrix.reset();
//        mMatrix.postScale(mScale,mScale,halfWidth,halfHeight);
        float scaleHeight = arrowBitmapHeight * mScale;
        float scaleWidth = arrowBitmapWidth * mScale;
        float dx = (width - arrowBitmapWidth * mScale) / 2;
        float dy = (height - arrowBitmapWidth * mScale) / 2;
        mMatrix.postTranslate(dx, dy);
        mMatrix.postScale(mScale,mScale,dx,dy);
        mMatrix.postRotate(mRotateDegress,dx + scaleWidth/2,dy + scaleHeight/2);
        canvas.drawBitmap(mOtherArrowBitmap,mMatrix,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(halfWidth,halfHeight,10,mPaint);
    }
    private int mRotateDegress = 0;
    private int mTranslateX = 1080;
    private int mTranslateY = 0;
    private float mScale = 0.5f;

    public void setmRotateDegress(int mRotateDegress) {
        this.mRotateDegress = mRotateDegress;
        invalidate();
    }

    public void setmTranslateX(int mTranslateX) {
        this.mTranslateX = mTranslateX;
        invalidate();
    }

    public void setmScale(float mScale) {
        this.mScale = mScale;
        invalidate();
    }

    public void setmTranslateY(int mTranslateY) {
        this.mTranslateY = mTranslateY;
        invalidate();
    }
}
