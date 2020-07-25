package com.aserbao.aserbaosandroid.kotlin.scopes

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/4/17
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.kotlin.scopes
 */
class ScopeData(var name:String,var age:Int){
    fun start(){
        println("start fun")
    }

    fun stop(){
        println("stop fun")
    }
}
