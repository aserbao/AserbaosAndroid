package com.aserbao.aserbaosandroid.aaSource.android.java.lang.thread.synchron

import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * 同步锁 synchronized
 */
class SynchronizedActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("synchronized同步对象",0))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

    var lock = Any()
    fun synchronizedObjec(){
        synchronized(lock){

        }
    }


}