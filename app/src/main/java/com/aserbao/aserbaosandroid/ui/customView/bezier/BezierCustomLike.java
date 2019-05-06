package com.aserbao.aserbaosandroid.ui.customView.bezier;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.bezier.evaluator.BezierEvaluator;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/6 2:18 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.customView.bezier
 */
public class BezierCustomLike extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Random random = new Random();
    private Bitmap mBitmap;
    private int mIconWidth;
    private int mIconHeight;
    private static PointF mPointF;
    private Path mPath;
    private PointF mPointF1;
    private PointF mPointF2;
    private PointF mPointStart;
    private Paint mPointPaint;
    private PointF mPointzEnd;
    private Matrix mMatrix;

    public BezierCustomLike(Context context) {
        this(context,null);
    }

    public BezierCustomLike(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierCustomLike(Context context,@Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.RED);
        mPointPaint.setAntiAlias(true);

        mMatrix = new Matrix();
    }


    public void addLikeIcon(int resId){
        mBitmap = BitmapFactory.decodeResource(getResources(), resId);
        mIconWidth = mBitmap.getWidth();
        mIconHeight = mBitmap.getHeight();
        startAnimator();
    }

    public void startAnimator(){
        //曲线的两个顶点
        mPointF1 = new PointF(
            random.nextInt(mWidth),
            random.nextInt(mHeight / 2) + mHeight / 2);
        mPointF2 = new PointF(
            random.nextInt(mWidth),
            random.nextInt(mHeight / 2));
        int startX = (mWidth - mIconWidth) / 2;
        int startY = mHeight - mIconHeight;
        mPointStart = new PointF(startX, startY);

        Path path = new Path();

        path.moveTo(startX,startY);
        mPointzEnd = new PointF(random.nextInt(mWidth), random.nextInt(mHeight / 2));

        //贝塞尔估值器
        BezierEvaluator evaluator = new BezierEvaluator(mPointF1, mPointF2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, mPointStart, mPointzEnd);
        animator.setDuration(3000);
        animator.addUpdateListener(new UpdateListener(new WeakReference<>(this),path));
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
    public void update(PointF pointF, Path path){
        mPointF = pointF;
        mPath = path;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPath != null) {
            canvas.drawPath(mPath,mPaint);
            mPointPaint.setColor(getResources().getColor(R.color.black50));
            canvas.drawCircle(mPointStart.x,mPointStart.y,20,mPointPaint);

            mPointPaint.setColor(getResources().getColor(R.color.red));
            canvas.drawCircle(mPointF1.x,mPointF1.y,20,mPointPaint);
            mPointPaint.setColor(getResources().getColor(R.color.red));
            canvas.drawCircle(mPointF2.x,mPointF2.y,20,mPointPaint);

            mPointPaint.setColor(getResources().getColor(R.color.black));
            canvas.drawCircle(mPointzEnd.x,mPointzEnd.y,20,mPointPaint);

            mMatrix.reset();
            mMatrix.postTranslate(mPointF.x,mPointF.y);
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);
        }
    }

    public static class UpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private WeakReference<BezierCustomLike> mView;
        private Path mPath;
        public UpdateListener(WeakReference<BezierCustomLike> view, Path path) {
            mView = view;
            mPath = path;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            BezierCustomLike bezierCustomLike = mView.get();
            if (bezierCustomLike != null) {
                mPath.lineTo(pointF.x,pointF.y);
                bezierCustomLike.update(pointF,mPath);
            }
        }
    }


}
