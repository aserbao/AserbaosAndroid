package com.aserbao.jetpack.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.jetpack.architecture.lifecycle.LifecycleActivity
import com.aserbao.jetpack.architecture.navigation.NavigationAct
import com.aserbao.jetpack.architecture.viewmodel.ViewModelActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * @Author:aserbao
 * @email：aserbao@163.com
 * @date:2020/12/14 21:32
 * @description: Jetpack architecture
 */
class ArchitectureAct : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("lifecycle", LifecycleActivity::class.java))
        mBaseRecyclerBean.add(BaseRecyclerBean("viewmodel", ViewModelActivity::class.java))
        mBaseRecyclerBean.add(BaseRecyclerBean("navigation", NavigationAct::class.java))
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }




}
