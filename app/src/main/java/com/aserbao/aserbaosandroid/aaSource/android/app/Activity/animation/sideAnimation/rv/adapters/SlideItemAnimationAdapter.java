package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.viewHolders.SlideItemViewHolder;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */

public class SlideItemAnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    protected Activity mActivity;
    protected List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    protected  IItemOnTouchCallBackListener mIItemOnTouchCallBackListener;
    private int viewType = StaticFinalValues.VIEW_HOLDER_TEXT;


    public SlideItemAnimationAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen,  IItemOnTouchCallBackListener itemOnTouchCallBackListener) {
        mContext = context;
        mActivity = activity;
        mBaseRecyclerBean = classBeen;
        mIItemOnTouchCallBackListener = itemOnTouchCallBackListener;
    }




    public int mOrientation = LinearLayoutManager.VERTICAL;
    public void setmOrientation(int orientation){
        mOrientation = orientation;
    }



    @Override
    public int getItemViewType(int position) {
        if (mBaseRecyclerBean != null && position < mBaseRecyclerBean.size()){
            return mBaseRecyclerBean.get(position).getViewType();
        }
        return StaticFinalValues.VIEW_HOLDER_TEXT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case StaticFinalValues.VIEW_HOLDER_TEXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.slide_item_rv_text_item, parent, false);
                return new SlideItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position);
        if (holder instanceof SlideItemViewHolder) {
            ((SlideItemViewHolder) holder).setDataSource(classBean,position,mIItemOnTouchCallBackListener);
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

    public interface IItemOnTouchCallBackListener{
        void onClickOrLongPress(boolean isLongPress,Bundle bundle);
        void onScollView(View view,float scrollX,int action,int comeFrom);
    }


}
