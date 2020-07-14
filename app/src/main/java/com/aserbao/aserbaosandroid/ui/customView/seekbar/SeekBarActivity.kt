package com.aserbao.aserbaosandroid.ui.customView.seekbar

import android.view.View
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class SeekBarActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("带气泡的SeekBar",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("带气泡的SeekBar",1))
        mBaseRecyclerBean.add(BaseRecyclerBean("带气泡的SeekBar",2))
        mBaseRecyclerBean.add(BaseRecyclerBean("带气泡的SeekBar",3))
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0-> addDisCreateSeekBar()
            1-> addDisCreateSeekBar1()
            2-> addDisCreateSeekBar2()
            3-> addDisCreateSeekBar3()
        }
    }

    fun addDisCreateSeekBar(){ addLayoutToFrameLayout(R.layout.seek_bar_bubble_1,false) }
    fun addDisCreateSeekBar1(){ addLayoutToFrameLayout(R.layout.seek_bar_bubble_2,false) }
    fun addDisCreateSeekBar2(){ addLayoutToFrameLayout(R.layout.seek_bar_bubble_3,false) }
    fun addDisCreateSeekBar3(){ addLayoutToFrameLayout(R.layout.seek_bar_bubble_4,false) }
}