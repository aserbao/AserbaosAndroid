package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators.baseItemAnimators;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

/**
 * 功能: 试一下RecyclerView.ItemAnimator的实现。
 *
 * @author aserbao
 * @date : On 2019-11-07 10:41
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators
 */
public class BaseItemAnimator extends RecyclerView.ItemAnimator {

    //当一个ViewHolder从RecyclerView里面消失时调用,不一定是移除，也有可能是move操作
    @Override
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo1) {
        return false;
    }

    //当一个ViewHolder从RecyclerView里面显示时调用,不一定是新增，也有可能是move操作
    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo1) {
        return false;
    }

    //没有调用notify而引起布局的改变，比如滑动
    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo1) {
        return false;
    }

    //item发生改变的时候调用
    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo1) {
        return false;
    }

    //统筹RecyclerView中所有的动画，统一启动执行,一般的思路是在前面几个函数调用中放入一个动画列表，在这个函数中统一执行
    @Override
    public void runPendingAnimations() {

    }

    //结束某一个item的动画
    @Override
    public void endAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {

    }

    //结束所有的动画
    @Override
    public void endAnimations() {

    }

    //动画是否执行过程中
    @Override
    public boolean isRunning() {
        return false;
    }
}
