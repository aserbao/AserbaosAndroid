package com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public abstract void baseViewHoldBinder(BaseViewHolder holder,int position);
}