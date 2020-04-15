package com.example.base.base.viewHolder

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener
import com.example.base.utils.data.ASourceUtil

class ImageViewHolder(itemView: View) : BaseClickViewHolder(itemView) {
    var mImageViewItem: ImageView? = null
    var mCommonTextView: TextView? = null

    init {
        mImageViewItem = itemView.findViewById(R.id.image_view_item);
        mCommonTextView = itemView.findViewById(R.id.common_text_view);
    }

    fun setDataSource(classBean: BaseRecyclerBean, position: Int, mIBaseRecyclerItemClickListener: IBaseRecyclerItemClickListener?) {
        super.setDataSource(position, mIBaseRecyclerItemClickListener)
        val tag = classBean.tag
        var name = classBean.name
        if (tag >= 0) {
            itemView.tag = tag
            name = if (TextUtils.isEmpty(name)) {
                tag.toString()
            } else {
                "$name $tag"
            }
        } else {
            name = if (TextUtils.isEmpty(name)) {
                tag.toString()
            } else {
                "$name $position"
            }
        }
        mCommonTextView!!.text = name
        mImageViewItem!!.setImageResource(classBean.imageSrc)
        val adapterPosition = adapterPosition
        mImageViewItem!!.transitionName = ASourceUtil.imageUrls[adapterPosition % ASourceUtil.imageUrls.size].toString()
    }
}