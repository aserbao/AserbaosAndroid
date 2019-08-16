package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-16 15:39
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base.viewHolder
 */
public class BaseClickViewHolder extends RecyclerView.ViewHolder {

    public BaseClickViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(int position, IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempFlag = 0;
                Object viewTag = v.getTag();
                if (viewTag instanceof Integer) {
                    int tag = (int) viewTag;
                    if (tag >= 0) {
                        tempFlag = tag;
                    } else {
                        tempFlag = position;
                    }
                } else {
                    tempFlag = position;
                }
                if (mIBaseRecyclerItemClickListener != null) {
                    mIBaseRecyclerItemClickListener.itemClickBack(v, tempFlag);
                }
            }
        });
    }

}
