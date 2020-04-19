package com.aserbao.aserbaosandroid.kotlin.scopes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * kotlin scope学习
 */
class KotlinScopeFunctionsActivity : BaseRecyclerViewActivity() {


    private val SCOPE_LET       = 1
    private val SCOPE_RUN       = 2
    private val SCOPE_WITH      = 3
    private val SCOPE_APPLY     = 4
    private val SCOPE_ALSO      = 5

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("let", SCOPE_LET))
        mBaseRecyclerBean.add(BaseRecyclerBean("run", SCOPE_RUN))
        mBaseRecyclerBean.add(BaseRecyclerBean("with", SCOPE_WITH))
        mBaseRecyclerBean.add(BaseRecyclerBean("apply", SCOPE_APPLY))
        mBaseRecyclerBean.add(BaseRecyclerBean("also", SCOPE_ALSO))
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
                SCOPE_LET   ->{
                    useLet()
                }
                SCOPE_RUN   ->{
                    useRun()
                }
                SCOPE_WITH  ->{
                    useWith()
                }
                SCOPE_APPLY ->{
                    useApply()
                }
                SCOPE_ALSO  ->{
                    useAlso()
                }
        }
    }


    private fun useAlso() {
        var returnResult = ScopeData("aserbao", 18).also {
            it.name = "aserbaocool"
            var name = it.name
            println("this name is $name")
            name.endsWith("cool")
        }
        println("use also the return values is $returnResult")
    }

    private fun useApply() {
        var returnResult = ScopeData("aserbao", 18).apply {
            println("this name is $name")
            name.endsWith("cool")
        }
        println("use apply the return values is $returnResult")
    }

    private fun useWith() {
        var returnResult = with(ScopeData("aserbao", 18)) {
            println("this name is $name")
            name = "aserbaocool"
            start()
            stop()
            name.endsWith("cool")
        }
        println("use with the return values is $returnResult")
    }

    /**
     * the same as
     * val scopeData = ScopeData("aserbao", 18)
     * scopeData.name = "aserbaocool"
     * scopeData.age = 19
     */
    private fun useLet() {
        var returnResult = ScopeData("aserbao", 18).let {
            it.name = "aserbaocool"
            mBaseRecyclerTv.setText(toString())
            it.name.endsWith("cool")
        }
        println("use let the return values is $returnResult")
    }

    private fun useRun() {
        val returnResult = ScopeData("aserbao", 18).run {
            name = "aserbaocool"
            name.endsWith("cool")
        }
        println("use run the return values is $returnResult")
    }




}
