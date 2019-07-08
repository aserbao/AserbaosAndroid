package com.aserbao.aserbaosandroid.base.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class BaseRecyclerViewActivityAdapter extends RecyclerView.Adapter<BaseRecyclerViewActivityAdapter.OpenGlViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    private IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener;

    public BaseRecyclerViewActivityAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen, IBaseRecyclerItemClickListener listener) {
        mContext = context;
        mActivity = activity;
        mBaseRecyclerBean = classBeen;
        mIBaseRecyclerItemClickListener = listener;
    }

    @Override
    public OpenGlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_activity_item, parent, false);
        return new OpenGlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OpenGlViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position);
        holder.mBaseRecyclerViewItemTv.setText(classBean.getName());
        int tag = classBean.getTag();
        if (tag > 0) {
            holder.mBaseRecyclerViewItemTv.setTag(tag);
        }
        holder.mBaseRecyclerViewItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIBaseRecyclerItemClickListener.itemClickBack(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mBaseRecyclerBean.size() > 0) {
            ret = mBaseRecyclerBean.size();
        }
        return ret;
    }

    class OpenGlViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.base_recycler_view_item_tv)
        TextView mBaseRecyclerViewItemTv;

        public OpenGlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
