package com.aserbao.aserbaosandroid.aaSource.java.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.aaSource.java.util.concurrent.FutureAct
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * @author: aserbao
 * @date:2020/12/1 10:25 AM
 * @package:com.aserbao.aserbaosandroid.aaSource.java
 * @describle: 
 */ 
class JavaUtilAct : BaseRecyclerViewActivity() {

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("Future的使用",FutureAct::class.java))
    }

}