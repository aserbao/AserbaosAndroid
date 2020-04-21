package com.aserbao.aserbaosandroid.ui.viewPager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aserbao.aserbaosandroid.R
import kotlinx.android.synthetic.main.first_fragment_layout.*

/*
* 作用：
* @author aserbao
* @date: on 2020/4/21
* @project: AserbaosAndroid
* @package: com.aserbao.aserbaosandroid.ui.viewPager.fragments
*/
class FirstFragment(): Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_fragment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstTv.setText((System.currentTimeMillis()/1000).toString() )
    }
}