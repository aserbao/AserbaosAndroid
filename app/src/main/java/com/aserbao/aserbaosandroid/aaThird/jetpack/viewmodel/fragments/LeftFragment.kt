package com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments

import android.os.Bundle
import android.view.View
import com.example.base.utils.random.RandomValue
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewbind.ViewBindBaseFragment
import kotlinx.android.synthetic.main.fragment_left.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments
 */
class LeftFragment : ViewBindBaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tip_tv.setText("LeftFragment")
        add_btn.setText("左边数据+1")
        add_btn.setOnClickListener {
            var randomName = com.example.base.utils.random.RandomValue.getChineseName()
            binding.tipTv.setText("当前数据为：\n " + randomName)
            model.changeString(randomName)
        }
    }
}