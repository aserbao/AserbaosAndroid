package com.aserbao.aserbaosandroid.functions.database.greenDao.rv.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.date.AppDateMgr;
import com.aserbao.aserbaosandroid.AserbaoApplication;
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
    public void refreshData(){
        mThing = ((AserbaoApplication) ((Activity)mContext).getApplication()).getDaoSession().getThingDao().loadAll();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_view_holder, viewGroup, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if(mThing.size() <= position ){
            return;
        }
        if(viewHolder instanceof TextViewHolder){
            final Thing thing = mThing.get(position);
            ((TextViewHolder) viewHolder).mTextViewHolderContentTv.setText(thing.getMessage());
            ((TextViewHolder) viewHolder).mTextViewHolderTimeTv.setText(AppDateMgr.formatFriendly(thing.getTime()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AserbaoApplication)((Activity) mContext).getApplication()).getDaoSession().delete(thing);
                    mThing.remove(thing);
                    notifyItemRemoved(position);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((AserbaoApplication)((Activity) mContext).getApplication()).getDaoSession().deleteAll(Thing.class);
                    return false;
                }
            });
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
