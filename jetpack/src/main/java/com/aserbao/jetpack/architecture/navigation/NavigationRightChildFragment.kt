package com.aserbao.jetpack.architecture.navigation

import android.view.View
import com.example.base.base.BaseRVFragment
import com.example.base.base.BaseRecyclerViewActivity

/*
* 作用：
* @author aserbao
* @date: on 2020/12/17
* @project: AserbaosAndroid
* @package: com.aserbao.jetpack.architecture.navigation
*/
class NavigationRightChildFragment: BaseRVFragment() {
    override fun initGetData() {
        setClassTip()
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }


}