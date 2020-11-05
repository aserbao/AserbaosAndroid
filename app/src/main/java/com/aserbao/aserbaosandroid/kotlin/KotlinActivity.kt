package com.aserbao.aserbaosandroid.kotlin

import android.util.Log
import android.view.View
import com.aserbao.aserbaosandroid.kotlin.base.KotlinBaseUseAct
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * kotlin 的使用
 */
class KotlinActivity : BaseRecyclerViewActivity() {
    companion object{
    }
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("kotlin的基础用法",KotlinBaseUseAct::class.java))
        mBaseRecyclerBean.add(BaseRecyclerBean("默认参数的的用法",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("inline函数的用法",1))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0->handlerDefaultArguments()
            1->handlerInline()
        }
    }

    /**
     * 内连函数的用法
     */
    private fun handlerInline() {
    }

    /**
     * 处理默认参数
     */
    fun handlerDefaultArguments(){
        fun defaultArguments(carNum:Int = 1,house:Int = 0,money:()-> Unit){
            Log.e(TAG, ": defaultArguments carNum= $carNum  hourse= $house");
        }
        defaultArguments{ Log.e(TAG, ": handlerDefaultArguments")}
        defaultArguments(0){ Log.e(TAG, ": handlerDefaultArguments")}
        defaultArguments(money = {Log.e(TAG, ": handlerDefaultArguments")})
    }


}
