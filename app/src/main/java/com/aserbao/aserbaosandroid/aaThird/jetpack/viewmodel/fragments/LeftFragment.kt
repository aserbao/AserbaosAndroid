package com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.SharedViewModel
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.SharedViewModels
import kotlinx.android.synthetic.main.fragment_left.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.fragments
 */
class LeftFragment : Fragment() {
    private lateinit var model: SharedViewModels
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity!!).get(SharedViewModels::class.java)
        model.getCurrentName().observe(this, Observer {
            top_tv.setText("左边数据为："+ it)
        })
        model.getCurrentRightName().observe(this, Observer {
            bottom_tv.setText("右边数据为："+ it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tip_tv.setText("LeftFragment")
        add_btn.setText("左边数据+1")
        /*top_tv.setText("左边数据为：" + model!!.leftData)
        bottom_tv.setText("右边数据为：" + model!!.rightData)
        ll_container.setBackgroundColor(Color.BLUE)
        add_btn.setOnClickListener {
            model!!.leftData ++;
            top_tv.setText("左边数据为：" + model!!.leftData)
        }*/
        add_btn.setOnClickListener {
            var randomName = RandomValue.getChineseName()
            tip_tv.setText("当前数据为：" + randomName)
            model.changeString(randomName)
        }
    }
}