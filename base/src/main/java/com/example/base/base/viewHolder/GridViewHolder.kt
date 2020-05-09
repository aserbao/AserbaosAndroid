package com.example.base.base.viewHolder

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.GridViewBean
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener
import kotlinx.android.synthetic.main.base_recycler_view_gridview_item.view.*
import kotlinx.android.synthetic.main.circle_image_item.view.*


/**
 * @see R.layout.base_recycler_view_gridview_item
 * @constructor
 */
class GridViewHolder(itemView: View) : BaseClickViewHolder(itemView) {
    var mGridViewBean:GridViewBean ?= null
    init {

    }

    fun setDataSource(classBean: BaseRecyclerBean, position: Int, mIBaseRecyclerItemClickListener: IBaseRecyclerItemClickListener?) {
        super.setDataSource(position, mIBaseRecyclerItemClickListener)
        mGridViewBean = classBean.getmGridViewBean()
        val tag = classBean.tag
        var name = mGridViewBean?.title
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
        itemView.item_tv.setText(name)
        initGridView()
    }

    private fun initGridView() {
        mGridViewBean?.apply {
            itemView.grid_view.adapter = MyAdapter(itemView.context,srcUrl)
        }
    }

    /**
     * @see  R.layout.circle_image_item
     * @property srcList MutableList<String>
     * @constructor
     */
    class MyAdapter(var context: Context, var srcList:MutableList<String>):BaseAdapter(){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view:View ?= convertView;
            var holder: ViewHolder? = null
            if (convertView == null) { // 第一次加载创建View，其余复用 View
                view = LayoutInflater.from(context).inflate(R.layout.circle_image_item, null)
                holder = ViewHolder()
                holder.imageView = view.image_view_item as ImageView
                convertView?.tag = holder
            } else { // 从标签中获取数据
//                holder = convertView.tag as ViewHolder
            }
            return view!!
        }

        override fun getItem(position: Int): String {
            return srcList.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            var ret = srcList.size
            return ret
        }
    }
    class ViewHolder{
        var imageView:ImageView ?= null
    }
}