package com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments

import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewbind.ViewBindBaseFragment
import kotlinx.android.synthetic.main.fragment_left.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments
 */
class RightFragment : ViewBindBaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tipTv.setText("RightFragment")
        binding.addBtn.setText("右数据+1")
        add_btn.setOnClickListener {
            var randomName = RandomValue.getChineseName()
            binding.tipTv.setText("当前数据为：\n " + randomName)
            model.changeRight(randomName)
        }
    }
}