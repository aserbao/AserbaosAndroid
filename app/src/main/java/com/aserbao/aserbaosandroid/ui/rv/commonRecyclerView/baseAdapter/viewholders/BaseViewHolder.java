package com.aserbao.aserbaosandroid.ui.rv.commonRecyclerView.baseAdapter.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public abstract void baseViewHoldBinder(BaseViewHolder holder,int position);
}