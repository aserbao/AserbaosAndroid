package com.example.base.base.viewHolder

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.utils.data.ASourceUtil
import kotlinx.android.synthetic.main.base_recycler_view_class_item.view.*

class ClassViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    fun setDataSource(mActivity: Activity, classBean: BaseRecyclerBean) {
        itemView.item_card_view.setBackgroundResource(ASourceUtil.getDrawable())
        itemView.item_tv.text = classBean.name
        itemView.setOnClickListener {
            val intent = Intent(mActivity, classBean.clazz)
            mActivity.startActivity(intent)
        }
    }


}