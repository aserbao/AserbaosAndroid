package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;

import butterknife.BindView;

public class ImageViewHolder extends BaseClickViewHolder {
        @BindView(R.id.image_view_item)
        ImageView mImageViewItem;

        @BindView(R.id.common_text_view)
        TextView mCommonTextView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setDataSource(BaseRecyclerBean classBean, int position, IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener){
            super.setDataSource(position,mIBaseRecyclerItemClickListener);

            int tag = classBean.getTag();
            String name = classBean.getName();
            if (tag >= 0) {
                itemView.setTag(tag);
                name = name + " "+ tag;
            }else{
                name = name + " "+ position;
            }
            mCommonTextView.setText(name);

            mImageViewItem.setImageResource(classBean.getImageSrc());

            int adapterPosition = getAdapterPosition();
            mImageViewItem.setTransitionName(String.valueOf(ASourceUtil.iamgeUrl[adapterPosition]));


        }

    }