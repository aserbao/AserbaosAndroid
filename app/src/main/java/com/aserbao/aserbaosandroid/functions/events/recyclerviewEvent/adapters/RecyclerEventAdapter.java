package com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/11
 * email: this is empty email
 */
public class RecyclerEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;

    public RecyclerEventAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_animation_item, viewGroup, false);
        return new RecyclerEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class RecyclerEventViewHolder extends RecyclerView.ViewHolder{

        public RecyclerEventViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
