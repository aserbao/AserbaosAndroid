package com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle

import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle
 */
class LifecycleActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        mBaseRecyclerTv.setText("不多说，自己看日志")
    }

    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLifecycle().addObserver(MyObserver())
    }

}