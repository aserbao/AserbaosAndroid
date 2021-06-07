package com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelFeatureBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.ColorSelVH;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.FootSelVH;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;

import java.util.ArrayList;
import java.util.List;

import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.CONTENT_TYPE;
import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.HEAD_TYPE;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */
public class SelFeatureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    protected List<SelFeatureBean> selFeatureBeans = new ArrayList<>();
    private ItemClickerListener mItemClickerListener;

    @Nullable
    private HeadFooterClickerListener mHeadFooterClickerListener;
    boolean isHasHeader = false;
    boolean isHasFooter = false;

    public SelFeatureAdapter(Context mContext, List<SelFeatureBean> mColorBeans, ItemClickerListener itemClickerListener) {
        this.mContext = mContext;
        this.selFeatureBeans = mColorBeans;
        this.mItemClickerListener = itemClickerListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHasHeader && position == 0){
            return HEAD_TYPE;
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
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_border_sel_round, parent, false);
        }
        return new SelFeatureVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SelFeatureVH) {
                if(isHasHeader) position --;
                ((SelFeatureVH)holder).setDataSource(selFeatureBeans.get(position), position, new ItemClickerListener() {
                    @Override
                    public void itemClick(SelFeatureBean beans, int position) {
                        for (SelFeatureBean mColorBean : selFeatureBeans) {
                            if (mColorBean != beans) {
                                mColorBean.isSel = false;
                            }
                        }
                        notifyDataSetChanged();
                        mItemClickerListener.itemClick(beans, position);
                    }
                });
        }else if(holder instanceof HeadSelVH){
            ((HeadSelVH)holder).setDataSource(position, mHeadFooterClickerListener);
        }else if(holder instanceof FootSelVH){
            ((FootSelVH) holder).setDataSource(position, mHeadFooterClickerListener);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (selFeatureBeans.size() > 0) {
            ret = selFeatureBeans.size();
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
        void itemClick(SelFeatureBean beans, int position);
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
