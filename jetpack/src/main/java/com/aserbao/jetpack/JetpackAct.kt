package com.aserbao.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.jetpack.architecture.ArchitectureAct
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * @Author:aserbao
 * @email：aserbao@163.com
 * @date:2020/12/14 21:30
 * @description: Jetpack系列
 */
class JetpackAct : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("Architecture",ArchitectureAct::class.java))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }




}
