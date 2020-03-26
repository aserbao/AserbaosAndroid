package com.aserbao.aserbaosandroid.aaThird.jetpack

import android.view.View
import com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle.LifecycleActivity
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.ViewModelActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

open class JetpackActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("lifecycle", LifecycleActivity::class.java))
        mBaseRecyclerBean.add(BaseRecyclerBean("viewmodel", ViewModelActivity::class.java))
    }


}
