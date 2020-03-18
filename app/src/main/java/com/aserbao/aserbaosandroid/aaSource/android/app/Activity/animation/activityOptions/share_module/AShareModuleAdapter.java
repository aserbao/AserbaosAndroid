package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.ClassViewHolder;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.ImageViewHolder;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.TextViewHolder;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-20 10:58
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module
 */
public class AShareModuleAdapter extends BaseRecyclerViewActivityAdapter {
    public static final int BOTTOM = 1;
    public static final int TOP = 2;

    private int mComeType;

    public AShareModuleAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen, IBaseRecyclerItemClickListener listener,int comeFrom) {
        super(context, activity, classBeen, listener);
        mComeType = comeFrom;
    }


    public void removePositionItem(int position){
        if (mBaseRecyclerBean.size() > position) {
            mBaseRecyclerBean.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }
    }

    public void removePositionItem(int startPosition,int endPosition){
        /*for (int i = startPosition; i <= endPosition; i++) {
            if (mBaseRecyclerBean.size() > i) {
                mBaseRecyclerBean.remove(i);
            }
        }*/
        int itemCount = endPosition - startPosition + 1;
        notifyItemRangeRemoved(startPosition,itemCount);
//        notifyItemRangeChanged(startPosition, itemCount);
//        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position);
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setDataSource(classBean,position,mIBaseRecyclerItemClickListener);
        }else if (holder instanceof ImageViewHolder){
            if (mComeType == TOP){
                holder.itemView.setVisibility(View.INVISIBLE);
            }else{
                holder.itemView.setVisibility(View.VISIBLE);
            }
            ((ImageViewHolder) holder).setDataSource(classBean,position,mIBaseRecyclerItemClickListener);
        }else if (holder instanceof ClassViewHolder){
            ((ClassViewHolder) holder).setDataSource(mActivity,classBean);
        }
    }

}
