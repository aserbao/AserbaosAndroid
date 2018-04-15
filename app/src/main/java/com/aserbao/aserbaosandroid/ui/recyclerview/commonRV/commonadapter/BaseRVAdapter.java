package com.aserbao.aserbaosandroid.ui.recyclerview.commonRV.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.ui.recyclerview.commonRV.commonViewholder.BaseRvViewholder;

import java.util.List;

/**
 * Created by aserbao on 2018 2018/4/15.23:17
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class BaseRvAdapter<T,K extends BaseRvViewholder> extends RecyclerView.Adapter<K> {

    private List<T> mData;
    private Context mContext;

    private final int ITEM_CONTENT = 0x00000010;


    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseRvViewHolder = null;
        mContext = parent.getContext();
        switch (viewType){
            case ITEM_CONTENT:

                break;
        }
        return null;
    }
    protected K createBaseRvViewHolder(View view){
        return ((K) new BaseRvViewholder(view));
    };

    @Override
    public void onBindViewHolder(K holder, int position) {

    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret;
    }
}
