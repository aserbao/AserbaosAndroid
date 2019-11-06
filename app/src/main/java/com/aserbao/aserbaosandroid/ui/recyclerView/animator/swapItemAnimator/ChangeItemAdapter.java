package com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator;

import android.app.Activity;
import android.content.Context;

import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import java.util.Collections;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-06 14:13
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.changeItemAnimator
 */
public class ChangeItemAdapter extends BaseRecyclerViewActivityAdapter implements ITouchCallBack{
    public ChangeItemAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen, IBaseRecyclerItemClickListener listener) {
        super(context, activity, classBeen, listener);
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBaseRecyclerBean,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        mBaseRecyclerBean.remove(position);
        //局部刷新,索引会混乱+集合越界
        notifyItemRemoved(position);
    }
}
