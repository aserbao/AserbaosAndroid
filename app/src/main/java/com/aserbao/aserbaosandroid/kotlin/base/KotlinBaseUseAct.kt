package com.aserbao.aserbaosandroid.kotlin.base

import android.util.Log
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.utils.data.StaticFinalValues

/**
 * @author: aserbao
 * @date:2020/11/4 2:51 PM
 * @package:com.aserbao.aserbaosandroid.kotlin.base
 * @describle:kotlin的基础用法
 */
class KotlinBaseUseAct : BaseRecyclerViewActivity() {


    private val KOTLIN_FOR = 0

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD, "循环的使用"))
        mBaseRecyclerBean.add(BaseRecyclerBean("forIndex的用法", KOTLIN_FOR))
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            KOTLIN_FOR->{
                handlefor()
            }
        }
    }

    /**
     * 使用for循环
     */
    fun handlefor(){
        val list = intArrayOf(1,2,3,4,5,6,7)
        list.forEachIndexed continuing@{
            index,context->
            Log.d(TAG, "the $index value:  $context")
            if(index ==3) return@continuing
        }
        Log.d(TAG, "continue is end")
        run breaking@{
            list.forEachIndexed{
                index,context->
                Log.d(TAG, "the $index value:  $context")
                if(index >=4) return@breaking
            }
        }
        Log.d(TAG, "break is end")

        list.forEachIndexed{
            index,context->
            Log.d(TAG, "the $index value:  $context")
            if(index >=4) return
        }
        Log.d(TAG, "return is end")
    }

}