package com.aserbao.aserbaosandroid.aaSource.android.material.floatView.floatView;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.base.utils.date.AppSysMgr;
import com.example.base.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;


public class FloatView extends FrameLayout {

    private static final int TOUCH_TIME_THRESHOLD = 150;

    private int width;
    private int mStatusBarHeight;
    private int mScreenHeight = AserbaoApplication.screenHeight;

    private float initialTouchX;
    private float initialTouchY;
    private float initialX;
    private float initialY;

    private long lastTouchDown;

    private boolean shouldStickToWall = true;

    private MoveAnimator animator;
    private OnRemoveListener onRemoveListener;
    private OnClickListener onClickListener;

    public void setOnRemoveListener(OnRemoveListener listener) {
        onRemoveListener = listener;
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public FloatView(Context context) {
        this(context, null);
        inflate(getContext(), R.layout.circle_50_50_iamge_item, this);
        initializeView();
    }

    public FloatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initializeView();
    }

    public FloatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    public void setShouldStickToWall(boolean shouldStick) {
        this.shouldStickToWall = shouldStick;
    }

    void notifyRemoved() {
        if (onRemoveListener != null) {
            onRemoveListener.onRemoved(this);
        }
    }

    private void initializeView() {
        mStatusBarHeight = AppSysMgr.getSysScreenStatusHeight(this.getContext());
        animator = new MoveAnimator();
        setClickable(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = getX();
                    initialY = getY();
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();
                    lastTouchDown = System.currentTimeMillis();
                    updateSize();
                    animator.stop();
                    break;
                case MotionEvent.ACTION_MOVE:
                    updateViewPosition(event);
                    break;
                case MotionEvent.ACTION_UP:
                    goToWall();
                    if (System.currentTimeMillis() - lastTouchDown < TOUCH_TIME_THRESHOLD) {
                        if (onClickListener != null) {
                            onClickListener.onClick(this);
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    private void updateViewPosition(MotionEvent event) {
        setX(initialX + event.getRawX() - initialTouchX);
        // 限制不可超出屏幕高度
        float desY = initialY + event.getRawY() - initialTouchY;
        if (desY < mStatusBarHeight) {
            desY = mStatusBarHeight;
        }
        if (desY > mScreenHeight - getHeight()) {
            desY = mScreenHeight - getHeight();
        }
        setY(desY);
    }


    private void updateSize() {
        width = DisplayUtil.getScreenWidth(getContext()) - this.getWidth();
    }


    public interface OnRemoveListener {
        void onRemoved(FloatView magnetView);
    }

    public interface OnClickListener {
        void onClick(FloatView magnetView);
    }

    public void goToWall() {
        if (shouldStickToWall) {
            int middle = width / 2;
            float nearestXWall = getX() >= middle ? width + DisplayUtil.dp2px(20) : -DisplayUtil.dp2px(20);
            animator.start(nearestXWall, getY());
        }
    }

    private void move(float deltaX, float deltaY) {
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }


    private class MoveAnimator implements Runnable {

        private Handler handler = new Handler(Looper.getMainLooper());
        private float destinationX;
        private float destinationY;
        private long startingTime;

        private void start(float x, float y) {
            this.destinationX = x;
            this.destinationY = y;
            startingTime = System.currentTimeMillis();
            handler.post(this);
        }

        @Override
        public void run() {
            if (getRootView() != null && getRootView().getParent() != null) {
                float progress = Math.min(1, (System.currentTimeMillis() - startingTime) / 400f);
                float deltaX = (destinationX - getX()) * progress;
                float deltaY = (destinationY - getY()) * progress;
                move(deltaX, deltaY);
                if (progress < 1) {
                    handler.post(this);
                }
            }
        }

        private void stop() {
            handler.removeCallbacks(this);
        }
    }
}
