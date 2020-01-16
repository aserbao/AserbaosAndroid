package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 16:50
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.viewHolder
 */
public class SeekBarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_seek_bar)
    SeekBar mItemSeekBar;
    @BindView(R.id.item_show_tv)
    TextView mItemShowTv;

    public SeekBarViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(BaseRecyclerBean classBean, int position, IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener){
        mItemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
