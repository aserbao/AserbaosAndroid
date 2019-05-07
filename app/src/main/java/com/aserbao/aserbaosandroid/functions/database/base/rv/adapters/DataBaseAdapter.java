package com.aserbao.aserbaosandroid.functions.database.base.rv.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.AUtils.utils.date.ADateMgr;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.base.rv.interfaces.ItemBackListener;
import com.aserbao.aserbaosandroid.functions.database.base.rv.viewHolder.TextViewHolder;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.mySql.beans.ThingDBController;

import java.util.ArrayList;
import java.util.List;

import static com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity.GREEN_DAO;
import static com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity.SQL_LITE;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/26
 * email: this is empty email
 */
public class DataBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Thing> mThing = new ArrayList<>();
    private Context mContext;
    private ItemBackListener mItemBackListener;

    public DataBaseAdapter(List<Thing> mThing, Context mContext,ItemBackListener itemBackListener) {
        this.mThing = mThing;
        this.mContext = mContext;
        mItemBackListener = itemBackListener;
    }

    public void addThingData(Thing data) {
        if (data != null) {
            mThing.add(data);
        }
        notifyDataSetChanged();
    }

    public void addNewThingData(List<Thing> data){
        mThing.clear();
        mThing.addAll(data);
        notifyDataSetChanged();
    }
    public void refreshData(int type){
        switch (type){
            case SQL_LITE:
                ThingDBController thingDBController = new ThingDBController(mContext);
                mThing = thingDBController.queryAll();
                break;
            case GREEN_DAO:
                mThing = ((AserbaoApplication) ((Activity)mContext).getApplication()).getDaoSession().getThingDao().loadAll();
                break;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if(mThing.size() <= position ){
            return;
        }
        if(viewHolder instanceof TextViewHolder){
            final Thing thing = mThing.get(position);
                ((TextViewHolder) viewHolder).mTextViewHolderContentTv.setText(thing.getMessage() + " Id 为：" + String.valueOf(thing.getId()));
            ((TextViewHolder) viewHolder).mTextViewHolderTimeTv.setText(ADateMgr.formatFriendly(thing.getTime()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mThing.remove(thing);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,mThing.size() - position);
                    mItemBackListener.onItemClick(thing);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemBackListener.onItemLongClick(thing);
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
