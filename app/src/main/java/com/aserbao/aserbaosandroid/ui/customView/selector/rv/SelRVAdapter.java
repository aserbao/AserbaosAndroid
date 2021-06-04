package com.aserbao.aserbaosandroid.ui.customView.selector.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelBeans;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */
public class SelRVAdapter extends RecyclerView.Adapter<ColorSelVH> {

    private Context mContext;
    protected List<SelBeans> mColorBeans = new ArrayList<>();
    private ItemClickerListener mItemClickerListener;
    private int lastSelPosition = -1;


    public SelRVAdapter(Context mContext, List<SelBeans> mColorBeans, ItemClickerListener mItemClickerListener) {
        this.mContext = mContext;
        this.mColorBeans = mColorBeans;
        this.mItemClickerListener = mItemClickerListener;
    }

    @Override
    public ColorSelVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_round, parent, false);
        return new ColorSelVH(view);
    }

    @Override
    public void onBindViewHolder(ColorSelVH holder, int position) {
        if (position < mColorBeans.size()){
            holder.setDataSource(mColorBeans.get(position), position, new ItemClickerListener() {
                @Override
                public void itemClick(SelBeans beans, int position) {
                    if(lastSelPosition > 0 && mColorBeans.size() > lastSelPosition){
                        mColorBeans.get(lastSelPosition).isSel = false;
                        notifyItemChanged(position);
                        notifyItemChanged(lastSelPosition);
                    }else{
                        for (SelBeans mColorBean : mColorBeans) {
                            if(mColorBean != beans){
                                mColorBean.isSel = false;
                            }
                        }
                        notifyDataSetChanged();
                    }
                    lastSelPosition = position;
                    mItemClickerListener.itemClick(beans,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mColorBeans.size() > 0) {
            ret = mColorBeans.size();
        }
        return ret;
    }


    public interface ItemClickerListener{
        void itemClick(SelBeans beans, int position);
    }

}
