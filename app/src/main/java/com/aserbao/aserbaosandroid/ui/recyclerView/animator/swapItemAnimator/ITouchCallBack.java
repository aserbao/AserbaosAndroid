package com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-06 14:04
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.changeItemAnimator
 */
public interface ITouchCallBack {
    //交换条目位置
    void onItemMove(int fromPosition,int toPosition);
    //删除条目
    void onItemDelete(int position);
}
