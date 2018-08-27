package com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveToDeleteAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public MoveToDeleteAda(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.normal_item, viewGroup, false);
        return new MoveChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MoveChildViewHolder) {
            ((MoveChildViewHolder) viewHolder).normalItem.setText(String.valueOf(i));
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class MoveChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.normal_item)
        TextView normalItem;
        public MoveChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
