package com.aserbao.aserbaosandroid.ui.recyclerview.aEffect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.CommonAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerview.aEffect.viewholder.ViewPagerHolder;
import com.aserbao.aserbaosandroid.ui.recyclerview.beans.CommenBean;

import java.util.List;

/**
 * Created by aserbao on 2018 2018/4/15.22:54
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class EffectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<CommenBean> mCommenBeanList;
    public EffectAdapter(Context context,List<CommenBean> commenBeanList) {
        mContext = context;
        mCommenBeanList = commenBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager,
                parent, false);
        return new ViewPagerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewPagerHolder) holder).setDataSource(position,mCommenBeanList);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
