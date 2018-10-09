package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.aserbao.aserbaosandroid.R;


/**
 * Created by RyanLee on 2017/12/8.
 */

public class GalleryRecyclerView extends RecyclerView {
    private int FLING_SPEED = 1000; // 滑动速度

    public static final int LinearySnapHelper = 0;
    public static final int PagerSnapHelper = 1;

    public boolean mHasWindowFocus = false;



    private ScrollManager mScrollManager;
    private GalleryItemDecoration mDecoration;

    public GalleryRecyclerView(Context context) {
        this(context, null);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.gallery_recyclerview);
        int helper = ta.getInteger(R.styleable.gallery_recyclerview_helper, LinearySnapHelper);
        ta.recycle();

        attachDecoration();
        attachToRecyclerHelper(helper);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        this.mHasWindowFocus = hasWindowFocus;

        if (getAdapter().getItemCount() <= 0) {
            return;
        }
        // 获得焦点后滑动至第0项，避免第0项的margin不对
//        if (hasWindowFocus) {
//            //scrollToPosition(0);
//            smoothScrollToPosition(StaticFinalValues.SEL_VIDEO_ROWS * 100 +);
//        } else {
//            scrollToPosition(0);
//        }

        mScrollManager.initScrollListener();

        if (mScrollManager != null) {
            mScrollManager.updateComsume();
        }
    }



    private void attachDecoration() {
        mDecoration = new GalleryItemDecoration();
        addItemDecoration(mDecoration);
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = balancelocity(velocityX);
        velocityY = balancelocity(velocityY);
        return super.fling(velocityX, velocityY);
    }

    /**
     * 返回滑动速度值
     *
     * @param velocity
     * @return
     */
    private int balancelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, FLING_SPEED);
        } else {
            return Math.max(velocity, -FLING_SPEED);
        }
    }

    /**
     * 连接RecyclerHelper
     * @param helper
     */
    private void attachToRecyclerHelper(int helper) {
        mScrollManager = new ScrollManager(this);
        mScrollManager.initSnapHelper(helper);
    }

    /**
     * 设置页面参数，单位dp
     *
     * @param pageMargin           默认：0dp
     * @param leftPageVisibleWidth 默认：50dp
     * @return
     */
    public GalleryRecyclerView initPageParams(int pageMargin, int leftPageVisibleWidth) {
        GalleryItemDecoration.mPageMargin = pageMargin;
        GalleryItemDecoration.mLeftPageVisibleWidth = leftPageVisibleWidth;
        return this;
    }

    /**
     * 设置滑动速度（像素/s）
     *
     * @param speed
     * @return
     */
    public GalleryRecyclerView initFlingSpeed(int speed) {
        this.FLING_SPEED = speed;
        return this;
    }

    /**
     * 设置动画因子
     *
     * @param factor
     * @return
     */
    public GalleryRecyclerView setAnimFactor(float factor) {
        AnimManager.getInstance().setmAnimFactor(factor);
        return this;
    }

    /**
     * 设置动画类型
     *
     * @param type
     * @return
     */
    public GalleryRecyclerView setAnimType(int type) {
        AnimManager.getInstance().setmAnimType(type);
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param mListener
     */
    public GalleryRecyclerView setOnItemClickListener(OnItemClickListener mListener) {
        if (mDecoration != null) {
            mDecoration.setOnItemClickListener(mListener);
        }
        return this;
    }

    public int getOrientation() {

        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
            } else {
                return ((LinearLayoutManager) getLayoutManager()).getOrientation();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }

    public LinearLayoutManager getLinearLayoutManager() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");

            } else {
                return (LinearLayoutManager) getLayoutManager();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }

    public int getScrolledPosition() {
        if (mScrollManager == null) {
            return 0;
        } else {
            return mScrollManager.getPosition();
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public boolean getHasWindowFocus() {
        return mHasWindowFocus;
    }

}
