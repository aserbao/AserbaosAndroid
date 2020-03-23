package com.example.base.base.viewHolder

import android.view.View
import com.example.base.base.beans.BaseRecyclerBean
import kotlinx.android.synthetic.main.base_recycler_view_holder_head.view.*

class HeadViewHolder(itemView: View?) : BaseClickViewHolder(itemView!!) {
    fun setDataSource(classBean: BaseRecyclerBean) {
        itemView.base_recycler_view_holder_head_tv.setText(classBean.name)
    }
}