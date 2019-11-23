package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators.baseItemAnimators.BaseSimpleItemAnimator;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-07 14:59
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators
 */
public class FadeInDownAnimator extends BaseSimpleItemAnimator {
    public static final int DURATION = 1500;

    @Override
    protected void preAnimateAdd(RecyclerView.ViewHolder holder) {

    }

    @Override
    protected void animateRemoveImpls(RecyclerView.ViewHolder holder) {
        View itemView = holder.itemView;
        ViewCompat.animate(itemView)
//            .setDuration(getRemoveDuration())
            .setDuration(DURATION)
            .alpha(0)
//            .translationY(itemView.getHeight() * 2)
            .translationX(itemView.getWidth() * 2)
            .setListener(new DefaultRemoveListener(holder))
            .setStartDelay(getRemoveDelay(holder))
            .start();
    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        View itemView = holder.itemView;
        ViewCompat.animate(itemView)
            .translationY(0)
            .alpha(1)
//            .setDuration(getAddDuration())
            .setDuration(DURATION)
            .setListener(new DefaultAddListener(holder))
            .setStartDelay(getAddDelay(holder))
            .start();
    }
}
