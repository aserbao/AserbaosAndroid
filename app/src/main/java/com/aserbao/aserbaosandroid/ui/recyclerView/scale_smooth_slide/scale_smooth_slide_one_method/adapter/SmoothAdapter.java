package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.library.CardAdapterHelper;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.common.viewHolders.SmoothItemViewHolder;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/25
 * email: this is empty email
 */
public class SmoothAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public SmoothAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.smooth_slide_item, viewGroup, false);
        mCardAdapterHelper.onCreateViewHolder(viewGroup,view);
        return new SmoothItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        mCardAdapterHelper.onBindViewHolder(viewHolder.itemView, position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return 1000;
    }
}
