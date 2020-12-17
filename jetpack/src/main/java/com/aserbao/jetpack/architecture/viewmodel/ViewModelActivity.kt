package com.aserbao.jetpack.architecture.viewmodel

import android.os.Bundle
import android.view.View
import com.aserbao.jetpack.R
import com.example.base.base.BaseRecyclerViewActivity

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.jetpack.architecture.viewmodel
 */
class ViewModelActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        addLayoutToFrameLayout(R.layout.fragment_left_and_right,true)
    }
    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {}

}