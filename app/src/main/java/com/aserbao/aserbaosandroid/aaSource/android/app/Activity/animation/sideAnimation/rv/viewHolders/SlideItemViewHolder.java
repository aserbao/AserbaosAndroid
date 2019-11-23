package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.viewHolders;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.CatchTouchCardView;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.BaseClickViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.base_recycler_view_item_tv)
        public TextView mBaseRecyclerViewItemTv;
        @BindView(R.id.base_rv_text_item_cv)
        public CatchTouchCardView mBaseRvTextItemCv;

        public SlideItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDataSource(BaseRecyclerBean classBean, int position, SlideItemAnimationAdapter.IItemOnTouchCallBackListener mIBaseRecyclerItemClickListener){
            int tag = classBean.getTag();
            String name = classBean.getName();
            if (tag >= 0) {
                itemView.setTag(tag);
                name = name + String.valueOf(tag);
            } else {
                name = name + String.valueOf(position);
            }
            mBaseRecyclerViewItemTv.setText(name);
            mBaseRvTextItemCv.setIItemOnTouchCallBackListener(new SlideItemAnimationAdapter.IItemOnTouchCallBackListener() {
                @Override
                public void onClickOrLongPress(boolean isLongPress, Bundle bundle) {

                }

                @Override
                public void onScollView(View view, float scrollX, int action, int comeFrom) {
                    if (mIBaseRecyclerItemClickListener != null) {
                        mIBaseRecyclerItemClickListener.onScollView(itemView,scrollX,action,comeFrom);
                    }
                }
            });
        }



}