package com.aserbao.aserbaosandroid.ui.customView.selector.rv.color;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelColorBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature.HeadSelVH;

import java.util.ArrayList;
import java.util.List;

import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.CONTENT_TYPE;
import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.FOOT_TYPE;
import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.HEAD_TYPE;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */
public class SelColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    protected List<SelColorBean> mColorBeans = new ArrayList<>();
    private ItemClickerListener mItemClickerListener;

    private HeadFooterClickerListener mHeadFooterClickerListener;
    boolean isHasHeader = false;
    boolean isHasFooter = false;
    private int maxShowLength = 0;

    public SelColorAdapter(Context mContext, List<SelColorBean> mColorBeans, ItemClickerListener itemClickerListener) {
        this.mContext = mContext;
        this.mColorBeans = mColorBeans;
        this.mItemClickerListener = itemClickerListener;
        this.maxShowLength = mColorBeans.size();
    }

    /**
     * 最多可显示Item数
     * @param maxShowLength，不包括头尾
     */
    public void setMaxShowLength(int maxShowLength) {
        this.maxShowLength = maxShowLength;
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
        switch (viewType){
            case HEAD_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_head, parent, false);
                return new HeadSelVH(view);
            case FOOT_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_foot, parent, false);
                return new FootSelVH(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_round, parent, false);
        }
        return new ColorSelVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ColorSelVH) {
            if (position < mColorBeans.size()) {
                ((ColorSelVH)holder).setDataSource(mColorBeans.get(position), position, new ItemClickerListener() {
                    @Override
                    public void itemClick(SelColorBean beans, int position) {
                        for (SelColorBean mColorBean : mColorBeans) {
                            if (mColorBean != beans) {
                                mColorBean.isSel = false;
                            }
                        }
                        notifyDataSetChanged();
                        mItemClickerListener.itemClick(beans, position);
                    }
                });
            }
        }else if(holder instanceof HeadSelVH){
            ((HeadSelVH)holder).setDataSource(position, mHeadFooterClickerListener);
        }else if(holder instanceof FootSelVH){
            ((FootSelVH) holder).setDataSource(position, mHeadFooterClickerListener);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mColorBeans.size() > 0) {
            ret = maxShowLength;
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
        void itemClick(SelColorBean beans, int position);
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
