package com.aserbao.aserbaosandroid.kotlin.scopes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                    Log.e(TAG, "----------------- useLet -----------------")
                    useLet()
                }
                SCOPE_RUN   ->{
                    Log.e(TAG, "----------------- useRun -----------------")
                    useRun()
                }
                SCOPE_WITH  ->{
                    Log.e(TAG, "----------------- useWith -----------------")
                    useWith()
                }
                SCOPE_APPLY ->{
                    Log.e(TAG, "----------------- useApply -----------------")
                    useApply()
                }
                SCOPE_ALSO  ->{
                    Log.e(TAG, "----------------- useAlso -----------------")
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


        val numbers = mutableListOf("one", "two", "three", "four", "five")

        val resultList = numbers.map { it.length }.filter { it > 3 }
        Log.e(TAG, "useJava" + "$resultList")
        numbers.map { it.length }.filter { it > 3 }.apply {
            Log.e(TAG, "useRun" + "$this")
        }

        // 确保上下文不为null
        val str: String? = "aserbao"
//        processNonNullString(str)       // compilation error: str can be null
        val length = str?.apply {
            processNonNullString(this)      // OK: 'it' is not null inside '?.let { }'
            this.length
        }
        processNonNullString(length.toString())

        // 为上下文定义新变量
        val numbers2 = listOf("one", "two", "three", "four")
        val modifiedFirstItem = numbers2.first().apply {
            Log.e(TAG, "The first item of the list is '$this'")
            if (this.length >= 5) this else "!" + this + "!"
        }.toUpperCase()
        Log.e(TAG, "First item after modifications: '$modifiedFirstItem'")


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


        val numbers = mutableListOf("one", "two", "three", "four", "five")

        val resultList = numbers.map { it.length }.filter { it > 3 }
        Log.e(TAG, "useJava" + "$resultList")
        with(numbers.map { it.length }.filter { it > 3 }) {
            Log.e(TAG, "useWith" + "$this")
        }


        val firstAndLast = with(numbers) {
            "The first element is ${first()}," +
                " the last element is ${last()}"
        }
        Log.e(TAG, "useWith $firstAndLast")
    }

    private fun useLet() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")

        val resultList = numbers.map { it.length }.filter { it > 3 }
        Log.e(TAG, "useJava" + "$resultList")
        numbers.map { it.length }.filter { it > 3 }.let {
            Log.e(TAG, "useLet" + "$it")
        }

        // 确保上下文不为null
        val str: String? = "aserbao"
//        processNonNullString(str)       // compilation error: str can be null
        val length = str?.let {
            processNonNullString(it)      // OK: 'it' is not null inside '?.let { }'
            it.length
        }
        processNonNullString(length.toString())

        // 为上下文定义新变量
        val numbers2 = listOf("one", "two", "three", "four")
        val modifiedFirstItem = numbers2.first().let { firstItem ->
            Log.e(TAG, "The first item of the list is '$firstItem'")
            if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
        }.toUpperCase()
        Log.e(TAG, "First item after modifications: '$modifiedFirstItem'")
    }

    private fun useRun() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")

        val resultList = numbers.map { it.length }.filter { it > 3 }
        Log.e(TAG, "useJava" + "$resultList")
        numbers.map { it.length }.filter { it > 3 }.run {
            Log.e(TAG, "useRun" + "$this")
        }

        // 确保上下文不为null
        val str: String? = "aserbao"
//        processNonNullString(str)       // compilation error: str can be null
        val length = str?.run {
            processNonNullString(this)      // OK: 'it' is not null inside '?.let { }'
            this.length
        }
        processNonNullString(length.toString())

        // 为上下文定义新变量
        val numbers2 = listOf("one", "two", "three", "four")
        val modifiedFirstItem = numbers2.first().run {
                Log.e(TAG, "The first item of the list is '$this'")
                if (this.length >= 5) this else "!" + this + "!"
        }.toUpperCase()
        Log.e(TAG, "First item after modifications: '$modifiedFirstItem'")


        // 非扩展函数
        val hexNumberRegex = run {
            val digits = "0-9"
            val hexDigits = "A-Fa-f"
            val sign = "+-"

            Regex("[$sign]?[$digits$hexDigits]+")
        }

        for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
            Log.e(TAG, " " + match.value)
        }


    }

    fun processNonNullString(result:String){
        Log.e(TAG, "processNonNullString$result")
    }



}
