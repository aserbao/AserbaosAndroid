package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


/**
 * Created by RyanLee on 2017/12/12.
 */

public class AnimManager {
    private static final String TAG = "AnimManager";

    private static AnimManager INSTANCE;

    public static final int ANIM_BOTTOM_TO_TOP = 0;
    public static final int ANIM_TOP_TO_BOTTOM = 1;

    private int mAnimType = ANIM_BOTTOM_TO_TOP; //动画类型
    private float mAnimFactor = 0.2f;   //变化因子

    public static AnimManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AnimManager();
        }
        return INSTANCE;
    }

    public void setAnimation(RecyclerView recyclerView, int position, float percent) {
        switch (mAnimType) {
            case ANIM_BOTTOM_TO_TOP:
                setBottomToTopAnim(recyclerView, position, percent);
                break;
            case ANIM_TOP_TO_BOTTOM:
                setTopToBottomAnim(recyclerView, position, percent);
                break;
            default:
                setBottomToTopAnim(recyclerView, position, percent);
                break;
        }
    }


    /**
     * 从下到上的动画效果
     *
     * @param recyclerView
     * @param position
     * @param percent
     */
    private void setBottomToTopAnim(RecyclerView recyclerView, int position, float percent) {
        View mCurView = recyclerView.getLayoutManager().findViewByPosition(position);       // 中间页
        View mRightView = recyclerView.getLayoutManager().findViewByPosition(position + 1); // 左边页
        View mLeftView = recyclerView.getLayoutManager().findViewByPosition(position - 1);  // 右边页


        if (percent <= 0.5) {
            if (mLeftView != null) {
                mLeftView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mLeftView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mCurView != null) {
                mCurView.setScaleX(1 - percent * mAnimFactor);
                mCurView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mRightView != null) {
                mRightView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mRightView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
        } else {
            if (mLeftView != null) {
                mLeftView.setScaleX(1 - percent * mAnimFactor);
                mLeftView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mCurView != null) {
                mCurView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mCurView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mRightView != null) {
                mRightView.setScaleX(1 - percent * mAnimFactor);
                mRightView.setScaleY(1 - percent * mAnimFactor);
            }
        }
    }


    /***
     * 从上到下的效果
     * @param recyclerView
     * @param position
     * @param percent
     */
    private void setTopToBottomAnim(RecyclerView recyclerView, int position, float percent) {
        View mCurView = recyclerView.getLayoutManager().findViewByPosition(position);       // 中间页
        View mRightView = recyclerView.getLayoutManager().findViewByPosition(position + 1); // 左边页
        View mLeftView = recyclerView.getLayoutManager().findViewByPosition(position - 1);  // 右边页

        if (percent <= 0.5) {
            if (mLeftView != null) {
                mLeftView.setScaleX(1 - percent * mAnimFactor);
                mLeftView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mCurView != null) {
                mCurView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mCurView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mRightView != null) {
                mRightView.setScaleX(1 - percent * mAnimFactor);
                mRightView.setScaleY(1 - percent * mAnimFactor);
            }

        } else {
            if (mLeftView != null) {
                mLeftView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mLeftView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mCurView != null) {
                mCurView.setScaleX(1 - percent * mAnimFactor);
                mCurView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mRightView != null) {
                mRightView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mRightView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
        }
    }

    public void setmAnimFactor(float mAnimFactor) {
        this.mAnimFactor = mAnimFactor;
    }

    public void setmAnimType(int mAnimType) {
        this.mAnimType = mAnimType;
    }
}
