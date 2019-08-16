package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.ConstantUtils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        public TextView mItemTv;
        @BindView(R.id.item_card_view)
        public CardView mItemCardView;
        public ClassViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setDataSource(Activity mActivity, BaseRecyclerBean classBean){
            mItemCardView.setBackgroundResource(ConstantUtils.getDrawable());
            mItemTv.setText(classBean.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, classBean.getClazz());
                    mActivity.startActivity(intent);
                }
            });
        }
    }