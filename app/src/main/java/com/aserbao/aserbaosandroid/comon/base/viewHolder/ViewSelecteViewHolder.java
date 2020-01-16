package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:23
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.viewHolder
 */
public class ViewSelecteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_rv)
    RecyclerView mRecyclerView;

    public ViewSelecteViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(BaseRecyclerBean baseRecyclerBean){

    }


}
