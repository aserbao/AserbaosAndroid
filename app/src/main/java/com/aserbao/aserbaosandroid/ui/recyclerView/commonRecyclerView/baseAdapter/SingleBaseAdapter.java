package com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter.viewholders.BaseViewHolder;
import com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter.viewholders.HeadViewHolder;


public class SingleBaseAdapter<K> extends BaseAdapter {

    private Context mContext;
    private int mFirstLayoutId = 0;
    public SingleBaseAdapter(Context context,int firstLayoutId) {
        mContext = context;
        mFirstLayoutId = firstLayoutId;
    }

    @Override
    public int baseGetItemViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder baseOnCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(mFirstLayoutId, viewGroup, false);
        return new HeadViewHolder(view);
    }

    @Override
    public void baseOnBindViewHolder(BaseViewHolder viewHolder, int i) {
        viewHolder.baseViewHoldBinder(viewHolder,i);
    }

    @Override
    public int baseGetItemCount() {
        return 0;
    }
}
