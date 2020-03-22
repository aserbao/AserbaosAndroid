package com.aserbao.aserbaosandroid.aaSource.android.view

import android.view.View
import com.aserbao.aserbaosandroid.aaSource.android.view.ViewGroup.ViewGroupActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class ViewActivity : BaseRecyclerViewActivity() {

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mBaseRecyclerBean.add(BaseRecyclerBean("ViewGroup", ViewGroupActivity::class.java))
    }

    override fun initGetData() {
    }


}
