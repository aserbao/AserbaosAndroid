package com.example.base.base.viewHolder

import android.view.View
import android.widget.TextView
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import kotlinx.android.synthetic.main.base_recycler_view_holder_head.view.*

class TestViewHolder(itemView: View?) : BaseClickViewHolder(itemView!!) {
    fun setDataSource(classBean: BaseRecyclerBean) {
        itemView.findViewById<TextView>(R.id.list_simple_tv1).setText(classBean.name)
    }
}