package com.aserbao.aserbaosandroid.aaThird.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class DataBindActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("DataBinding By ViewModel",DataBindActivity::class.java))
    }

}
