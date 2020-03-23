package com.example.base.base.viewHolder

import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.base.R
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.VHSeekBarBean
import com.example.base.base.interfaces.IBaseRvItemInSeekBarListener

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
        if (max > 0) mItemSeekBar!!.max = max
        if (defaultPosition > 0) mItemSeekBar!!.progress = defaultPosition
        mItemDesTv!!.text = mVhSeekBarBean!!.getmDescription()
        mItemSeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mItemShowTv!!.text = progress.toString()
                if (mIBaseRvItemInSeekBarListener != null) {
                    mIBaseRvItemInSeekBarListener!!.onProgressChanged(seekBar, progress, false, tag)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    init {
        mItemDesTv =  itemView.findViewById(R.id.sb_des_tv)
        mItemShowTv =  itemView.findViewById(R.id.item_show_tv)
        mItemSeekBar =  itemView.findViewById(R.id.item_seek_bar)
    }
}