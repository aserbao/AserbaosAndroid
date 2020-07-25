package com.aserbao.aserbaosandroid.ui.viewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.ui.viewPager.fragments.FirstFragment
import kotlinx.android.synthetic.main.view_pager_layout.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_pager_layout)
        initView()
    }

    private fun initView() {
        aViewPager.apply {
            adapter = object : FragmentPagerAdapter(supportFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int) = when(position){
                        0 ->  FirstFragment.newInstance()
                        1 ->  FirstFragment.newInstance()
                        2 ->  FirstFragment.newInstance()
                        else -> null
                    }!!

                override fun getCount(): Int {
                    return 3
                }

                override fun getPageTitle(position: Int) = when(position){
                    0 -> "first"
                    1 -> "second"
                    2 -> "three"
                    else ->"else"
                }
            }
        }
    }
}
