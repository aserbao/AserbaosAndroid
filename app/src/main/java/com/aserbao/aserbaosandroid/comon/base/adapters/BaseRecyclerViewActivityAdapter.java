package com.aserbao.aserbaosandroid.comon.base.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.ClassViewHolder;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.ImageViewHolder;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.TextViewHolder;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */

public class BaseRecyclerViewActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    protected Activity mActivity;
    protected List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    protected IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener;
    private int viewType = StaticFinalValues.VIEW_HOLDER_TEXT;


    public BaseRecyclerViewActivityAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen, IBaseRecyclerItemClickListener listener) {
        mContext = context;
        mActivity = activity;
        mBaseRecyclerBean = classBeen;
        mIBaseRecyclerItemClickListener = listener;
    }

    public int mOrientation = LinearLayoutManager.VERTICAL;
    public void setmOrientation(int orientation){
        mOrientation = orientation;
    }

    public void remove(int position){
        if (mBaseRecyclerBean != null && mBaseRecyclerBean.size() > position){
            mBaseRecyclerBean.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,getItemCount());
//            notifyDataSetChanged();
        }
    }

    public void removeRange(int position,int count){
        if (mBaseRecyclerBean != null && mBaseRecyclerBean.size() >= count + position){
            for (int i = count-1; i >= position; i--) {
                mBaseRecyclerBean.remove(i);
            }
            notifyItemRangeRemoved(position,count);
        }
    }

    public void add(int position,BaseRecyclerBean baseRecyclerBean){
        if (mBaseRecyclerBean != null) {
            mBaseRecyclerBean.add(position,baseRecyclerBean);
            notifyItemInserted(position);
//            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBaseRecyclerBean != null ){
            return mBaseRecyclerBean.get(position % mBaseRecyclerBean.size()).getViewType();
        }
        return StaticFinalValues.VIEW_HOLDER_TEXT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case StaticFinalValues.VIEW_HOLDER_TEXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_text_item, parent, false);
                return new TextViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_IMAGE_100H:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_vertical_image_item, parent, false);
                if (mOrientation == LinearLayoutManager.HORIZONTAL){
                    view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_horizontal_image_item, parent, false);
                }
                return new ImageViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_CIRCLE_IMAGE_ITEM:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_circle_50_50_image_item, parent, false);
                return new ImageViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_CLASS:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_class_item, parent, false);
                return new ClassViewHolder(view);
            case StaticFinalValues.VIEW_BLEND_MODE:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_class_item, parent, false);
                return new ClassViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position % mBaseRecyclerBean.size());
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setDataSource(classBean,holder.getAdapterPosition(),mIBaseRecyclerItemClickListener);
        }else if (holder instanceof ImageViewHolder){
            ((ImageViewHolder) holder).setDataSource(classBean,position,mIBaseRecyclerItemClickListener);
        }else if (holder instanceof ClassViewHolder){
            ((ClassViewHolder) holder).setDataSource(mActivity,classBean);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mBaseRecyclerBean.size() > 0) {
            ret = mBaseRecyclerBean.size();
        }
        return ret;
    }




}
