package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;

import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 3:20 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest
 */
public class DoubleAdapter extends RecyclerView.Adapter<NestItemViewHolder> {

    private Context mContext;

    public DoubleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NestItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.include_rv_item, viewGroup, false);
        return new NestItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NestItemViewHolder nestItemViewHolder, int i) {
        nestItemViewHolder.setDataSource(mContext);
    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
