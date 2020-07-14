package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.progressBar

import android.view.View
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class ProgressBarActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("ProgressBar的简单使用",0))
    }
    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0 -> addLayoutToFrameLayout(R.layout.progress_bar_layout,false)
        }
    }
}