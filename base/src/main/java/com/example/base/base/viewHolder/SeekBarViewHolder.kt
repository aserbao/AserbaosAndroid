package com.example.base.base.viewHolder

import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.VHSeekBarBean
import com.example.base.base.interfaces.IBaseRvItemInSeekBarListener
import kotlinx.android.synthetic.main.base_recycler_view_seek_bar_item.view.*

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 16:50
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.viewHolder
 */
class SeekBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mItemSeekBar: SeekBar? = null
    var mItemShowTv: TextView? = null
    var mItemDesTv: TextView? = null
    private var mIBaseRvItemInSeekBarListener: IBaseRvItemInSeekBarListener? = null
    var mVhSeekBarBean: VHSeekBarBean? = null
    fun setDataSource(classBean: BaseRecyclerBean) {
        mVhSeekBarBean = classBean.getmVHSeekBarBean()
        if (mVhSeekBarBean == null) return
        mIBaseRvItemInSeekBarListener = mVhSeekBarBean!!.getmIBaseRvItemInSeekBarListener()
        val tag = mVhSeekBarBean!!.getmTag()
        val max = mVhSeekBarBean!!.getmMax()
        val defaultPosition = mVhSeekBarBean!!.getmDefaultPosition()
        itemView.itemShowTv?.let { 
            it.text = defaultPosition.toString()
        }
        if (max > 0) mItemSeekBar!!.max = max
        if (defaultPosition > 0) mItemSeekBar!!.progress = defaultPosition
        mItemDesTv!!.text = mVhSeekBarBean!!.getmDescription()
        mItemSeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mItemShowTv!!.text = progress.toString()
                if (mIBaseRvItemInSeekBarListener != null) {
                    mIBaseRvItemInSeekBarListener!!.onProgressChanged(seekBar, progress, fromUser, tag)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    init {
        mItemDesTv =  itemView.sb_des_tv
        mItemShowTv =  itemView.itemShowTv
        mItemSeekBar =  itemView.item_seek_bar
    }
}