package com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveToDeleteAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private Context mContext;

    public MoveToDeleteAda(Context context) {
        mContext = context;
        for (int i = 0; i < 100; i++) {
            mList.add(i);
        }
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
            ((MoveChildViewHolder) viewHolder).normalItem.setText(String.valueOf(mList.get(i)));
            ((MoveChildViewHolder) viewHolder).setPosition(i);
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class MoveChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.normal_item)
        TextView normalItem;
        @BindView(R.id.tv_group_del)
        TextView tvGroupDel;
        public MoveChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvGroupDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    Log.e("test", "onClick: delete" + position );
                }
            });
        }
        private int position ;
        public void setPosition(int p){
            position = p;
        }


    }
}
