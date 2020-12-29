package com.aserbao.jetpack.architecture.navigation

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.aserbao.jetpack.R
import com.example.base.base.BaseRVFragment
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/*
* 作用：
* // A 导航到 B fragment
* B的进入动画：enter = R.anim.activity_top_to_screen_anim
* A的退出动画：exit = R.anim.activity_screen_to_bottom_anim
* // 从B 退出到 A fragment
* A的出现动画：popEnter = R.anim.activity_bottom_to_screen_anim
* B的退出动画：popExit = R.anim.activity_screen_to_top_anim
* @author aserbao
* @date: on 2020/12/17
* @project: AserbaosAndroid
* @package: com.aserbao.jetpack.architecture.navigation
*/
class NavigationHomeFragment: BaseRVFragment() {
    private val TAG_TOP = 0
    private val TAG_LEFT = 1
    private val TAG_BOTTOM = 2
    private val TAG_RIGHT = 3

    override fun initGetData() {
        mBaseRecyclerBean.clear()
        setClassTip()
        mBaseRecyclerBean.add(BaseRecyclerBean("top", TAG_TOP))
        mBaseRecyclerBean.add(BaseRecyclerBean("left",TAG_LEFT))
        mBaseRecyclerBean.add(BaseRecyclerBean("bottom",TAG_BOTTOM))
        mBaseRecyclerBean.add(BaseRecyclerBean("right",TAG_RIGHT))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            TAG_TOP ->{
                val options = navOptions {
                    anim {
                        enter = R.anim.activity_top_to_screen_anim
                        exit = R.anim.activity_screen_to_bottom_anim
                        popEnter = R.anim.activity_bottom_to_screen_anim
                        popExit = R.anim.activity_screen_to_top_anim
                    }
                }
//                findNavController().navigate(R.id.NavigationTopFragment,null,options)
                findNavController().navigate(R.id.CommonUIActivity,null,options)
            }
            TAG_LEFT ->{
                val options = navOptions {
                    anim {
                        enter = R.anim.activity_left_to_screen_anim
                        exit = R.anim.activity_screen_to_right_anim
                        popEnter = R.anim.activity_right_to_screen_anim
                        popExit = R.anim.activity_screen_to_left_anim
                    }
                }
                findNavController().navigate(R.id.NavigationLeftFragment,null,options)
            }
            TAG_BOTTOM->{
                val options = navOptions {
                    anim {
                        enter = R.anim.activity_bottom_to_screen_anim
                        exit = R.anim.activity_screen_to_top_anim
                        popEnter = R.anim.activity_top_to_screen_anim
                        popExit = R.anim.activity_screen_to_bottom_anim
                    }
                }
                findNavController().navigate(R.id.NavigationBottomFragment,null,options)
            }
            TAG_RIGHT->{
                val options = navOptions {
                    anim {
                        enter = R.anim.activity_right_to_screen_anim
                        exit = R.anim.activity_screen_to_left_anim
                        popEnter = R.anim.activity_left_to_screen_anim
                        popExit = R.anim.activity_screen_to_right_anim
                    }
                }
                findNavController().navigate(R.id.NavigationRightFragment,null,options)
            }
        }
    }
}