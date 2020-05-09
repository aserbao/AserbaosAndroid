package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.GridView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.GridViewBean
import com.example.base.utils.data.StaticFinalValues

class GridViewActivity : BaseRecyclerViewActivity() {


    override fun initGetData() {
        var mStrings : MutableList<String> = ArrayList()
        for (i in 0..10){
            mStrings.add("https://www.baidu.com/img/bd_logo1.png")
        }
        mBaseRecyclerBean.add(BaseRecyclerBean(0,StaticFinalValues.VIEW_GRID_VIEW_ITME, GridViewBean(mStrings,"GridView的基本用法")))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {}

}
