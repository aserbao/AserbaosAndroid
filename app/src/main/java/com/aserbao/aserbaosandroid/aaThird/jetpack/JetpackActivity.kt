package com.aserbao.aserbaosandroid.aaThird.jetpack

import android.view.View
import com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle.LifecycleActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

open class JetpackActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mBaseRecyclerBean.add(BaseRecyclerBean("lifecycle", LifecycleActivity::class.java))
    }

    override fun initGetData() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
