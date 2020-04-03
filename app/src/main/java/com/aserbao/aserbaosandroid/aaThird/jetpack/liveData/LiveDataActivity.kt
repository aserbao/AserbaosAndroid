package com.aserbao.aserbaosandroid.aaThird.jetpack.liveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class LiveDataActivity : BaseRecyclerViewActivity() {
    lateinit var useMediatorLiveData:UseMediatorLiveData


    override fun initGetData() {
        useMediatorLiveData = ViewModelProvider(this).get(UseMediatorLiveData::class.java)
        mBaseRecyclerBean.add(BaseRecyclerBean("改变1号",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("改变2号",1))
        useMediatorLiveData.isChange.observe(this, Observer {
            mBaseRecyclerTv.setText(it.toString())
        })
    }
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0 -> useMediatorLiveData.isChangeText.value = !(useMediatorLiveData.isChangeText.value)!!
            1 -> useMediatorLiveData.isChangeSize.value = !(useMediatorLiveData.isChangeSize.value)!!
        }
    }
}
