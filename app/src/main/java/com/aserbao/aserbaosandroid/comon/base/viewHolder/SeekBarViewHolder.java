package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.beans.VHSeekBarBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRvItemInSeekBarListener;

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
    @BindView(R.id.sb_des_tv)
    TextView mItemDesTv;
    private IBaseRvItemInSeekBarListener mIBaseRvItemInSeekBarListener;

    public SeekBarViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    VHSeekBarBean mVhSeekBarBean;
    public void setDataSource(BaseRecyclerBean classBean){
        mVhSeekBarBean = classBean.getmVHSeekBarBean();
        if (mVhSeekBarBean == null) return;
        mIBaseRvItemInSeekBarListener = mVhSeekBarBean.getmIBaseRvItemInSeekBarListener();
        int tag = mVhSeekBarBean.getmTag();
        int max = mVhSeekBarBean.getmMax();
        int defaultPosition = mVhSeekBarBean.getmDefaultPosition();
        if (max > 0) mItemSeekBar.setMax(max);
        if (defaultPosition > 0) mItemSeekBar.setProgress(defaultPosition);
        mItemDesTv.setText(mVhSeekBarBean.getmDescription());
        mItemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mItemShowTv.setText(progress);
                mIBaseRvItemInSeekBarListener.onProgressChanged(seekBar,progress,false,tag);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
