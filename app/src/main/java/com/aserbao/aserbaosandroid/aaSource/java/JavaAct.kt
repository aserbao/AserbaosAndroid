package com.aserbao.aserbaosandroid.aaSource.java

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.aaSource.java.util.JavaUtilAct
import com.aserbao.aserbaosandroid.aaSource.java.util.concurrent.FutureAct
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * @author: aserbao
 * @date:2020/12/1 10:24 AM
 * @package:com.aserbao.aserbaosandroid.aaSource.java
 * @describle: Java
 */
class JavaAct : BaseRecyclerViewActivity() {

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("JavaUtil的使用", JavaUtilAct::class.java))
    }

}