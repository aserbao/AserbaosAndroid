package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadViewHolder extends BaseClickViewHolder {
        @BindView(R.id.base_recycler_view_holder_head_tv)
        public TextView mBaseRecyclerViewHolderHeadTv;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDataSource(BaseRecyclerBean classBean){
            String name = classBean.getName();
            mBaseRecyclerViewHolderHeadTv.setText(name);
        }

}