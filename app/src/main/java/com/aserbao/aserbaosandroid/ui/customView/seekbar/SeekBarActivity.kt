package com.aserbao.aserbaosandroid.ui.customView.seekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.ui.customView.seekbar.discreate.DiscreteSeekBar
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class SeekBarActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("带气泡的SeekBar",0))
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0-> addDisCreateSeekBar()
        }
    }

    fun addDisCreateSeekBar(){
        addLayoutToFrameLayout(R.layout.custom_view_discreteseekbar,false)
    }
}