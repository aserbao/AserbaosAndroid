package com.aserbao.aserbaosandroid.ui.customView.selector.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelBeans;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */
public class SelRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //头部布局
    public static final int HEAD_TYPE = 0;
    //尾部布局
    public static final int FOOT_TYPE = 1;
    //内容
    public static final int CONTENT_TYPE = 2;

    private Context mContext;
    protected List<SelBeans> mColorBeans = new ArrayList<>();
    private ItemClickerListener mItemClickerListener;

    @Nullable
    private HeadFooterClickerListener mHeadFooterClickerListener;
    private int lastSelPosition = -1;
    boolean isHasHeader = false;
    boolean isHasFooter = false;

    public SelRVAdapter(Context mContext, List<SelBeans> mColorBeans, ItemClickerListener itemClickerListener) {
        this.mContext = mContext;
        this.mColorBeans = mColorBeans;
        this.mItemClickerListener = itemClickerListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHasHeader && position == 0){
            return HEAD_TYPE;
        }
        if(isHasFooter && position == getItemCount() - 1){
            return FOOT_TYPE;
        }
        return CONTENT_TYPE;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_round, parent, false);
        switch (viewType){
            case HEAD_TYPE:
                return new HeadSelVH(view);
        }
        return new ColorSelVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ColorSelVH) {
            if (position < mColorBeans.size()) {
                ((ColorSelVH)holder).setDataSource(mColorBeans.get(position), position, new ItemClickerListener() {
                    @Override
                    public void itemClick(SelBeans beans, int position) {
                        if (lastSelPosition > 0 && mColorBeans.size() > lastSelPosition) {
                            mColorBeans.get(lastSelPosition).isSel = false;
                            notifyItemChanged(position);
                            notifyItemChanged(lastSelPosition);
                        } else {
                            for (SelBeans mColorBean : mColorBeans) {
                                if (mColorBean != beans) {
                                    mColorBean.isSel = false;
                                }
                            }
                            notifyDataSetChanged();
                        }
                        lastSelPosition = position;
                        mItemClickerListener.itemClick(beans, position);
                    }
                });
            }
        }else if(holder instanceof HeadSelVH){
            ((HeadSelVH)holder).setDataSource(mColorBeans.get(position), position, new ItemClickerListener() {
                @Override
                public void itemClick(SelBeans beans, int position) {

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
        if(isHasHeader){
            ret ++;
        }
        if(isHasFooter){
            ret ++;
        }
        return ret;
    }


    public interface ItemClickerListener{
        void itemClick(SelBeans beans, int position);
    }


    public interface HeadFooterClickerListener{
        void itemClick(boolean isHead);
    }


    public void setHasHeader(boolean hasHeader) {
        isHasHeader = hasHeader;
    }

    public void setHasFooter(boolean hasFooter) {
        isHasFooter = hasFooter;
    }

    public void setmHeadFooterClickerListener(HeadFooterClickerListener mHeadFooterClickerListener) {
        this.mHeadFooterClickerListener = mHeadFooterClickerListener;
    }
}
