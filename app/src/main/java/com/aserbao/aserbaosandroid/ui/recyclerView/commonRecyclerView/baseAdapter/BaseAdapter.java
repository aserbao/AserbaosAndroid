package com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter.viewholders.BaseViewHolder;


public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder>{
   public static final int BASE_HEAD_ITEM = 0;
    public static final int BASE_CONTENT_ITEM = 50;
    public static final int BASE_FOOT_ITEM = 100;

    @Override
    public int getItemViewType(int position) {
        return baseGetItemViewType(position);
    }



    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.base_item_1, viewGroup, false);
        return baseOnCreateViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
        baseOnBindViewHolder(viewHolder,i);
    }

    @Override
    public int getItemCount() {
        return baseGetItemCount();
    }

    public abstract int baseGetItemViewType(int position);
    public abstract BaseViewHolder baseOnCreateViewHolder(@NonNull ViewGroup viewGroup, int i);
    public abstract void baseOnBindViewHolder(BaseViewHolder viewHolder, int i);
    public abstract int baseGetItemCount();



}
