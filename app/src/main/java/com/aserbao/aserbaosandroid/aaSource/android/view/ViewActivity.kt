package com.aserbao.aserbaosandroid.aaSource.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.aaSource.android.view.ViewGroup.ViewGroupActivity
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean

class ViewActivity : BaseRecyclerViewActivity() {

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mBaseRecyclerBean.add(BaseRecyclerBean("ViewGroup",ViewGroupActivity::class.java))
    }

    override fun initGetData() {
    }


}
