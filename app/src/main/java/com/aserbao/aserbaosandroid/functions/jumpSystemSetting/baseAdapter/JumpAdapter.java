package com.aserbao.aserbaosandroid.functions.jumpSystemSetting.baseAdapter;

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

public class JumpAdapter extends RecyclerView.Adapter<JumpAdapter.JumpBaseAdapter> {

    private Context mContext;

    public JumpAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public JumpBaseAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_item_1, viewGroup, false);
        return new JumpBaseAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JumpBaseAdapter jumpBaseAdapter, int i) {
        jumpBaseAdapter.mBaseItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class JumpBaseAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.base_item_tv)
        TextView mBaseItemTv;
        public JumpBaseAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
