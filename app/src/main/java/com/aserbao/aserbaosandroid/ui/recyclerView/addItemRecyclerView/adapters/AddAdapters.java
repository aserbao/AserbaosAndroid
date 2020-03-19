package com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/16
 * email: this is empty email
 */
public class AddAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int CONTENT = 0;
    public static final int FOOTER = 1;


    private List<String> stringList = new ArrayList<>();
    private Context mContext;

    public AddAdapters(List<String> stringList, Context mContext) {
        this.stringList = stringList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FOOTER;
        } else {
            return CONTENT;
        }
    }

    public void  addItem(String s){
        stringList.add(0,s);
        notifyItemInserted(1);
    }

    public void addTopItem(String s){
        for (int i = 0; i < 10; i++) {
            stringList.add(s);
            notifyItemInserted(stringList.size());
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case FOOTER:
                view = LayoutInflater.from(mContext).inflate(R.layout.foot_empty_item, viewGroup, false);
                return new FootViewHolder(view);
            case CONTENT:
                view = LayoutInflater.from(mContext).inflate(R.layout.add_item_tv, viewGroup, false);
                return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ContentViewHolder){
            int index = i - 1;
            if(index < stringList.size()) {
                String s = stringList.get(index);
                ((ContentViewHolder) viewHolder).mAddContentItemTv.setText(s);
            }
        }else if(viewHolder instanceof FootViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        int ret = 1;
        if (stringList != null) {
            ret = stringList.size() + ret;
        }
        return ret;
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.add_content_item_tv)
        TextView mAddContentItemTv;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foot_item_tv)
        public TextView mFootItemTv;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
