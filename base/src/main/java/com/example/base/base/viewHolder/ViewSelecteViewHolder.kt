package com.example.base.base.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:23
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.viewHolder
 */
class ViewSelecteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mRecyclerView: RecyclerView? = null

    fun setDataSource(baseRecyclerBean: BaseRecyclerBean?) {}

    init {
        mRecyclerView = itemView.findViewById(R.id.item_rv)
    }
}