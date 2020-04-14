package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_two_method.library;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.base.utils.screen.DisplayUtil;

public class ScrollManager {
    private static final String TAG = "ScrollManager";

    private GalleryRecyclerView mGalleryRecyclerView;

    private LinearSnapHelper mLinearySnapHelper;
    private PagerSnapHelper mPagerSnapHelper;

    private int mPosition = 0;

    // 使偏移量为左边距 + 左边Item的可视部分宽度
    private int mConsumeX = 0;
    private int mConsumeY = 0;
    // 滑动方向
    private int slideDirct = SLIDE_RIGHT;

    private static final int SLIDE_LEFT = 1;    // 左滑
    private static final int SLIDE_RIGHT = 2;   // 右滑
    private static final int SLIDE_TOP = 3;     // 上滑
    private static final int SLIDE_BOTTOM = 4;  // 下滑
    private float mScale = 0.9f;


    public ScrollManager(GalleryRecyclerView mGalleryRecyclerView) {
        this.mGalleryRecyclerView = mGalleryRecyclerView;
    }

    /**
     * 初始化SnapHelper
     *
     * @param helper
     */
    public void initSnapHelper(int helper) {
        switch (helper) {
            case GalleryRecyclerView.LinearySnapHelper:
                mLinearySnapHelper = new LinearSnapHelper();
                mLinearySnapHelper.attachToRecyclerView(mGalleryRecyclerView);
                break;
            case GalleryRecyclerView.PagerSnapHelper:
                mPagerSnapHelper = new PagerSnapHelper();
                mPagerSnapHelper.attachToRecyclerView(mGalleryRecyclerView);
                break;
        }
    }

    /**
     * 监听RecyclerView的滑动
     */
    public void initScrollListener() {
        GalleryScrollerListener mScrollerListener = new GalleryScrollerListener();
        mGalleryRecyclerView.addOnScrollListener(mScrollerListener);
    }

    public void updateComsume() {
        mConsumeX += DisplayUtil.dip2px(GalleryItemDecoration.mLeftPageVisibleWidth + GalleryItemDecoration.mPageMargin * 2);
        mConsumeY +=  DisplayUtil.dip2px(GalleryItemDecoration.mLeftPageVisibleWidth + GalleryItemDecoration.mPageMargin * 2);
        Log.d(TAG, "updateComsume mConsumeX=" + mConsumeX);
    }


    class GalleryScrollerListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.d(TAG, "newState=" + newState);
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (!mGalleryRecyclerView.getHasWindowFocus()) {
                return;
            }

            if (mGalleryRecyclerView.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                onHoritiontalScroll(recyclerView, dx);
            } else {
                onVerticalScroll(recyclerView, dy);
            }
        }
    }

    /**
     * 垂直滑动
     *
     * @param recyclerView
     * @param dy
     */
    private void onVerticalScroll(final RecyclerView recyclerView, int dy) {
        mConsumeY += dy;

        if (dy > 0) {
            slideDirct = SLIDE_BOTTOM;
        } else {
            slideDirct = SLIDE_TOP;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemHeight的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeY = GalleryItemDecoration.mItemComusemY;
                // 获取当前的位置
                int position = getPosition(mConsumeY, shouldConsumeY);
                float offset = (float) mConsumeY / (float) shouldConsumeY;     // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                // 避免offset值取整时进一，从而影响了percent值
                if (offset >= mGalleryRecyclerView.getLinearLayoutManager().findFirstVisibleItemPosition() + 1 && slideDirct == SLIDE_BOTTOM) {
                    return;
                }
                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);

                Log.d(TAG, "offset=" + offset + "; mConsumeY=" + mConsumeY + "; shouldConsumeY=" + shouldConsumeY);


                // 设置动画变化
                AnimManager.getInstance().setAnimation(recyclerView, position, percent);
            }
        });
    }

    /**
     * 水平滑动
     *
     * @param recyclerView
     * @param dx
     */
    private void onHoritiontalScroll(final RecyclerView recyclerView, final int dx) {
        Log.d(TAG, "mConsumeX=" + mConsumeX + "; dx=" + dx);
        mConsumeX += dx;

        if (dx > 0) {
            // 右滑
            slideDirct = SLIDE_RIGHT;
        } else {
            // 左滑
            slideDirct = SLIDE_LEFT;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemWidth的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeX = GalleryItemDecoration.mItemComusemX;
                // 获取当前的位置
                int position = getPosition(mConsumeX, shouldConsumeX);

                float offset = (float) mConsumeX / (float) shouldConsumeX;     // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）

                // 避免offset值取整时进一，从而影响了percent值
                LinearLayoutManager linearLayoutManager = mGalleryRecyclerView.getLinearLayoutManager();
                if (offset >= linearLayoutManager.findFirstVisibleItemPosition() + 1 && slideDirct == SLIDE_RIGHT) {
                    return;
                }

                // 获取当前页移动的百分值
                float percent = Math.abs(offset - ((int) offset));

                int visibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                View leftView = linearLayoutManager.findViewByPosition(visibleItemPosition - 1);
                View currentView = linearLayoutManager.findViewByPosition(visibleItemPosition );
                View rightView = linearLayoutManager.findViewByPosition(visibleItemPosition + 1);
                float scaleY = (1 - mScale) * percent + mScale;
                float scaleY1 = (mScale - 1) * percent + 1;
                if(scaleY > scaleY1){}
                    scaleY1 = 1.0f;
                    scaleY = 0.9f;
                if (leftView != null) {
                    leftView.setScaleY(scaleY);
                }
                if (currentView != null) {
                    currentView.setScaleY(scaleY1);
                }
                if (rightView != null) {
                    rightView.setScaleY(scaleY);
                }
                Log.e(TAG, "scaleY=" + scaleY + ";scaleY1 :" + scaleY1 +  "; percent=" + percent + "; mConsumeX=" + mConsumeX + "; shouldConsumeX=" + shouldConsumeX + "; position=" + position);

                // 设置动画变化
//                AnimManager.getInstance().setAnimation(recyclerView, position, percent);
            }
        });

    }


    /**
     * 获取位置
     *
     * @param mConsumeX      实际消耗距离
     * @param shouldConsumeX 理论消耗距离
     * @return
     */
    private int getPosition(int mConsumeX, int shouldConsumeX) {
        float offset = (float) mConsumeX / (float) shouldConsumeX;
        int position = Math.round(offset);        // 四舍五入获取位置
        mPosition = position;
        return position;
    }

    public int getPosition() {
        return mPosition;
    }
}
