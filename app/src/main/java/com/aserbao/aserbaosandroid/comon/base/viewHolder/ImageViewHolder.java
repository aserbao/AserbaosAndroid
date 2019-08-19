package com.aserbao.aserbaosandroid.comon.base.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;

import butterknife.BindView;

public class ImageViewHolder extends BaseClickViewHolder {
        @BindView(R.id.image_view_item)
        ImageView mImageViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setDataSource(BaseRecyclerBean classBean, int position, IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener){
            super.setDataSource(position,mIBaseRecyclerItemClickListener);
            mImageViewItem.setImageResource(classBean.getImageSrc());

            int adapterPosition = getAdapterPosition();
            mImageViewItem.setTransitionName(String.valueOf(ImageSource.iamgeUrl[adapterPosition]));
        }

    }