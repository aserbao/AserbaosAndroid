package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 3:20 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest
 */
public class DoubleAdapter extends RecyclerView.Adapter<NestItemViewHolder> {
    private static final String TAG = "DoubleAdapter";

    private Context mContext;

    IItemOnLongClickListener mIItemOnLongClickListener;
    public DoubleAdapter(Context mContext,IItemOnLongClickListener iItemOnLongClickListener) {
        this.mContext = mContext;
        mIItemOnLongClickListener = iItemOnLongClickListener;
    }

    @NonNull
    @Override
    public NestItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.include_rv_item, viewGroup, false);
        return new NestItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NestItemViewHolder nestItemViewHolder, int i) {
        Log.e(TAG, "onBindViewHolder: position = " + i );
        nestItemViewHolder.setDataSource(mContext,mIItemOnLongClickListener);
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull NestItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(TAG, "onViewDetachedFromWindow: " );
    }

    @Override
    public void onViewAttachedToWindow(@NonNull NestItemViewHolder holder) {
        Log.e(TAG, "onViewAttachedToWindow: " );
        super.onViewAttachedToWindow(holder);
    }
}
