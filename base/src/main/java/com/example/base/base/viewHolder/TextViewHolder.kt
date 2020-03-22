package com.aserbao.aserbaosandroid.comon.base.viewHolder

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener
import com.example.base.base.viewHolder.BaseClickViewHolder

class TextViewHolder(itemView: View) : BaseClickViewHolder(itemView) {
    var mBaseRecyclerViewItemTv: TextView? = null

    fun setDataSource(classBean: BaseRecyclerBean, position: Int, mIBaseRecyclerItemClickListener: IBaseRecyclerItemClickListener?) {
        super.setDataSource(position, mIBaseRecyclerItemClickListener)
        val tag = classBean.tag
        var name = classBean.name
        if (tag >= 0) {
            itemView.tag = tag
            name = name + tag.toString()
        } else {
            name = name + position.toString()
        }
        mBaseRecyclerViewItemTv!!.text = name
    }

    init {
        mBaseRecyclerViewItemTv = itemView.findViewById(R.id.base_recycler_view_item_tv)
    }
}