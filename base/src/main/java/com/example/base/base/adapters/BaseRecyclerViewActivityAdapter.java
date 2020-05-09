package com.example.base.base.adapters;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.R;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener;
import com.example.base.base.viewHolder.ClassViewHolder;
import com.example.base.base.viewHolder.GridViewHolder;
import com.example.base.base.viewHolder.HeadViewHolder;
import com.example.base.base.viewHolder.ImageViewHolder;
import com.example.base.base.viewHolder.SeekBarViewHolder;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.TextViewHolder;
import com.example.base.utils.data.StaticFinalValues;

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

    public void setmOrientation(int orientation) {
        mOrientation = orientation;
    }

    public void remove(int position) {
        if (mBaseRecyclerBean != null && mBaseRecyclerBean.size() > position) {
            mBaseRecyclerBean.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
//            notifyDataSetChanged();
        }
    }

    public void removeRange(int position, int count) {
        if (mBaseRecyclerBean != null && mBaseRecyclerBean.size() >= count + position) {
            for (int i = count - 1; i >= position; i--) {
                mBaseRecyclerBean.remove(i);
            }
            notifyItemRangeRemoved(position, count);
        }
    }

    public void add(int position, BaseRecyclerBean baseRecyclerBean) {
        if (mBaseRecyclerBean != null) {
            mBaseRecyclerBean.add(position, baseRecyclerBean);
            notifyItemInserted(position);
//            notifyDataSetChanged();
        }
    }

    public void addRange(int startPosition, List<BaseRecyclerBean> list) {
        if (mBaseRecyclerBean != null) {
            mBaseRecyclerBean.addAll(startPosition, list);
            notifyItemRangeInserted(startPosition, list.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBaseRecyclerBean != null) {
            return mBaseRecyclerBean.get(position % mBaseRecyclerBean.size()).getViewType();
        }
        return StaticFinalValues.VIEW_HOLDER_TEXT;
    }

    public int getSpanSize(int position){
        if (mBaseRecyclerBean != null) {
            int viewType = mBaseRecyclerBean.get(position % mBaseRecyclerBean.size()).getViewType();
            switch (viewType){
                case StaticFinalValues.VIEW_HOLDER_CLASS:
                    return  1;
                default:
                    return 3;
            }
        }
        return 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case StaticFinalValues.VIEW_HOLDER_HEAD:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_holder_head, parent, false);
                return new HeadViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_TEXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_text_item, parent, false);
                return new TextViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_IMAGE_100H:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_vertical_image_item, parent, false);
                if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                    view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_horizontal_image_item, parent, false);
                }
                return new ImageViewHolder(view);
            case StaticFinalValues.VIEW_FULL_IMAGE_ITEM:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_full_image_item, parent, false);
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
            case StaticFinalValues.VIEW_SEEK_BAR:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_seek_bar_item, parent, false);
                return new SeekBarViewHolder(view);
            case StaticFinalValues.VIEW_SELECTE_POSITION:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_selecte_view_item, parent, false);
                return new SeekBarViewHolder(view);
            case StaticFinalValues.VIEW_GRID_VIEW_ITME:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_gridview_item, parent, false);
                return new GridViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position % mBaseRecyclerBean.size());
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setDataSource(classBean, holder.getAdapterPosition(), mIBaseRecyclerItemClickListener);
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).setDataSource(classBean, position, mIBaseRecyclerItemClickListener);
        } else if (holder instanceof ClassViewHolder) {
            ((ClassViewHolder) holder).setDataSource(mActivity, classBean);
        } else if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder) holder).setDataSource(classBean);
        } else if (holder instanceof SeekBarViewHolder){
            ((SeekBarViewHolder) holder).setDataSource(classBean);
        } else if(holder instanceof GridViewHolder){
            ((GridViewHolder) holder).setDataSource(classBean,position,mIBaseRecyclerItemClickListener);
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
