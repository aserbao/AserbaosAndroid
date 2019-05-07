package com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.bezier.evaluator.BezierEvaluator;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.beans.TreeLine;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/6 2:18 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.customView.bezier
 */
public class BezierCustomLike extends View implements View.OnClickListener{

    public static final int INT_REFRESH_INTERVAL = 30;
    private int mWidth;
    private int mHeight;


    public BezierCustomLike(Context context) {
        this(context,null);
    }

    public BezierCustomLike(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierCustomLike(Context context,@Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mCustomHandler.sendEmptyMessageDelayed(0,INT_REFRESH_INTERVAL);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCustomHandler.removeCallbacks(null);
        mCustomHandler.removeMessages(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
    List<TreeLine> mTreeLineList = new ArrayList<>();
    public void addLike(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        TreeLine treeLine = new TreeLine(mHeight, mWidth, bitmap, new IFinishedListener() {
            @Override
            public void animationFinished(TreeLine treeLine) {
                mTreeLineList.remove(treeLine);
            }
        });
        mTreeLineList.add(treeLine);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (TreeLine treeLine : mTreeLineList) {
            treeLine.draw(canvas);
            Log.e(TAG, "onDraw: " + mTreeLineList.size() );
        }
    }

    private CustomHandler  mCustomHandler = new CustomHandler(new WeakReference<>(this));
    private static final String TAG = "BezierCustomLike";

    @Override
    public void onClick(View v) {
        for (int i = 0; i < 10; i++) {
            addLike();
        }
    }

    public static class CustomHandler extends Handler{

        WeakReference<BezierCustomLike> mBezierCustomLikeWeakReference;
        public CustomHandler(WeakReference<BezierCustomLike> bezierCustomLikeWeakReference) {
            mBezierCustomLikeWeakReference = bezierCustomLikeWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            BezierCustomLike bezierCustomLike = mBezierCustomLikeWeakReference.get();
            if (bezierCustomLike != null) {
                bezierCustomLike.postInvalidate();
                Log.e(TAG, "handleMessage: "  );
                sendEmptyMessageDelayed(0,INT_REFRESH_INTERVAL);
            }
        }
    }



}
