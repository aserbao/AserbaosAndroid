package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.progressBar

import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class ProgressBarActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("ProgressBar的简单使用",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("自定义圆形进度条",1))
    }

    var circularProgressView:CircularProgressView ?= null
    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0 -> addLayoutToFrameLayout(R.layout.progress_bar_layout,false)
            1 -> {

                if(!isLongClick){
                    circularProgressView = CircularProgressView(this)
                    addViewToFrameLayout(circularProgressView,false,false,true)
                }else{
                    circularProgressView?.setProgress(80,15*1000)
                }
            }
        }
    }



}