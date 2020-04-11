package com.aserbao.aserbaosandroid.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class KotlinActivity : BaseRecyclerViewActivity() {
    companion object{
        val INT_OBJECT = 0
    }
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("object的用法",0))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

}
