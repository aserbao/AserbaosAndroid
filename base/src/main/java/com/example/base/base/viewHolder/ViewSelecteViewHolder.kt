package com.example.base.base.viewHolder

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.VHSelecteBean
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener
import com.getremark.base.kotlin_ext.setVisible
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.base_recycler_view_selecte_view_item.view.*

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:23
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.viewHolder
 */
class ViewSelecteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),IBaseRecyclerItemClickListener {
    var mVHSelBean:VHSelecteBean ?= null

    fun setDataSource(context: Context, activity : Activity, classBean: BaseRecyclerBean, position: Int) {
        mVHSelBean = classBean.getmVHSelBean()
        mVHSelBean?.let {
            itemView.item_tv.setText(it.desc)
        }

        itemView.sel_btn.singleClick {
            var selected = itemView.sel_btn.isSelected
            if(selected){
                itemView.sel_btn.setText("展开")
                itemView.itemRv.setVisible(false)
            }else{
                itemView.itemRv.setVisible(true)
                itemView.sel_btn.setText("收起")
                initRV(context,activity)
            }
            itemView.sel_btn.isSelected = !selected
        }
    }

    fun initRV(context: Context, activity: Activity){
        mVHSelBean?.let {
            var baseRecyclerBeans:MutableList<BaseRecyclerBean> = ArrayList();
            it.getClassBean().forEachIndexed {
                i, s ->
                baseRecyclerBeans.add(BaseRecyclerBean(s.key))
            }
            itemView.itemRv.adapter = BaseRecyclerViewActivityAdapter(context,activity, baseRecyclerBeans,this)
            itemView.itemRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        }
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mVHSelBean?.apply {
            var classBeen = getClassBean().get(position)
            itemView.item_tv.setText(desc + " sel key = " + classBeen.key + " value = " + classBeen.value)
            getiSelItemListener().selItemClick(view,position,isLongClick, classBeen)
        }
    }


}