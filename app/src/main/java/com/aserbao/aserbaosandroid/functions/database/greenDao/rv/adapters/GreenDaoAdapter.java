package com.aserbao.aserbaosandroid.functions.database.greenDao.rv.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.greenDao.rv.viewHolder.TextViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/26
 * email: 1142803753@qq.com
 */
public class GreenDaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Thing> mThing = new ArrayList<>();
    private Context mContext;

    public GreenDaoAdapter(List<Thing> mThing, Context mContext) {
        this.mThing = mThing;
        this.mContext = mContext;
    }

    public void addThingData(Thing data) {
        if (data != null) {
            mThing.add(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_view_holder, viewGroup, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(mThing.size() <= position ){
            return;
        }
        if(viewHolder instanceof TextViewHolder){
            Thing thing = mThing.get(position);
            ((TextViewHolder) viewHolder).mTextViewHolderContentTv.setText(thing.getMessage());
            ((TextViewHolder) viewHolder).mTextViewHolderTimeTv.setText(thing.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mThing != null) {
            ret = mThing.size();
        }
        return ret;
    }
}
