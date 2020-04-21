package com.aserbao.aserbaosandroid.ui.viewPager.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/4/21
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.viewPager.adapters
 */
class SimplePagerAdapter(fm: FragmentManager,
                         var fragments:MutableList<Fragment>)
    : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

}