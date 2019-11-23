package com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-06 14:05
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.changeItemAnimator
 */
public class SimpleItemTouchCallBack extends ItemTouchHelper.Callback {
    private ITouchCallBack mCallBack;
    //左滑删除的
    private boolean mSwipeEnable = true;

    public SimpleItemTouchCallBack(ITouchCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }


    public void moveList(List<RecyclerView.ViewHolder> viewHolderList){
    }
    
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许向左滑动
        int swipe = ItemTouchHelper.LEFT;
        //设置
        return makeMovementFlags(drag,swipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        //通知适配器,两个子条目位置发生改变
        if (viewHolder != null && viewHolder1 != null) {
            mCallBack.onItemMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }


    /**
     * 支持长按拖动,默认是true
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 支持滑动,即可以调用到onSwiped()方法,默认是true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }

    /**
     * 设置是否支持左滑删除
     * @param enable
     */
    public void setmSwipeEnable(boolean enable) {
        this.mSwipeEnable = enable;
    }
}
