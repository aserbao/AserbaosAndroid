package com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.beans;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.animation.LinearInterpolator;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.bezier.evaluator.BezierEvaluator;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.BezierCustomLike;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.IFinishedListener;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.AnimationListener;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.HTextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/6 8:17 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.beans
 */
public class TreeLine {
    public static final int INT_ANIMATION_TIME = 5000;

    private final Random random;
    private final int mHeight,mWidth;
    private final Bitmap mBitmap;
    private final int mIconWidth,mIconHeight;
    private final Paint mPaint,mPointPaint;
    private final Matrix mMatrix;
    Path mPath;
    private PointF mPointF1,mPointF2,mPointStart,mPointEnd,mPointF;
    private IFinishedListener mIFinishedListener;

    public TreeLine(int height , int width, Bitmap bitmap, IFinishedListener iFinishedListener) {
        random = new Random();
        mHeight = height;
        mWidth = width;
        mBitmap = bitmap;
        mIconWidth = bitmap.getWidth();
        mIconHeight = bitmap.getHeight();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.RED);
        mPointPaint.setAntiAlias(true);

        mMatrix = new Matrix();
        mIFinishedListener = iFinishedListener;

        startAnimator();
    }

    public void startAnimator(){
        //曲线的两个顶点
        int randomY = random.nextInt(mHeight / 2);
        mPointF1 = new PointF(
            random.nextInt(mWidth),
            random.nextInt(mHeight * 2 / 3) );
        mPointF2 = new PointF(
            random.nextInt(mWidth),
            randomY);
        int startX = (mWidth - mIconWidth) / 2;
        int startY = mHeight - mIconHeight;
        mPointStart = new PointF(startX, startY);

        Path path = new Path();
        path.moveTo(startX,startY);
        mPointEnd = new PointF(random.nextInt(mWidth), 0);

        //贝塞尔估值器
        BezierEvaluator evaluator = new BezierEvaluator(mPointF1, mPointF2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, mPointStart, mPointEnd);
        animator.setDuration(INT_ANIMATION_TIME);
        animator.addUpdateListener(new UpdateListener(new WeakReference<>(this)));
        animator.addListener(new TreeLineAnimationListener(new WeakReference<>(this),new WeakReference<>(mIFinishedListener)));
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    public void draw(Canvas canvas){
        if (mPath != null) {
            canvas.drawPath(mPath,mPaint);
            /*mPointPaint.setColor(Color.BLACK);
            canvas.drawCircle(mPointStart.x,mPointStart.y,20,mPointPaint);

            mPointPaint.setColor(Color.RED);
            canvas.drawCircle(mPointF1.x,mPointF1.y,20,mPointPaint);
            mPointPaint.setColor(Color.RED);
            canvas.drawCircle(mPointF2.x,mPointF2.y,20,mPointPaint);

            mPointPaint.setColor(Color.BLACK);
            canvas.drawCircle(mPointEnd.x,mPointEnd.y,20,mPointPaint);*/

            /*mMatrix.reset();
            mMatrix.postTranslate(mPointF.x,mPointF.y);
            canvas.drawBitmap(mBitmap,mMatrix,mPaint);*/
        }
    }


    public static class UpdateListener implements ValueAnimator.AnimatorUpdateListener {
        WeakReference<TreeLine> mTreeLine;

        public  UpdateListener(WeakReference<TreeLine> tWeakReference) {
            mTreeLine = tWeakReference;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            TreeLine treeLine = mTreeLine.get();
            if (treeLine != null) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                treeLine.update(pointF);
            }
        }
    }


    public static class TreeLineAnimationListener extends AnimatorListenerAdapter {
        WeakReference<TreeLine> mTreeLine;
        WeakReference<IFinishedListener> mIFinishedListenerWeakReference;

        public TreeLineAnimationListener(WeakReference<TreeLine> treeLine, WeakReference<IFinishedListener> iFinishedListenerWeakReference) {
            mTreeLine = treeLine;
            mIFinishedListenerWeakReference = iFinishedListenerWeakReference;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            if (mIFinishedListenerWeakReference != null) {
                IFinishedListener iFinishedListener = mIFinishedListenerWeakReference.get();
                if (iFinishedListener != null) {
                    TreeLine treeLine = mTreeLine.get();
                    if (treeLine != null) {
                        iFinishedListener.animationFinished(treeLine);
                    }
                }
            }
        }
    }

    private void update(PointF pointF) {
        mPath.lineTo(pointF.x,pointF.y);
    }


}
