package com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/18
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel
 */
class SharedViewModel : ViewModel() {

    val data: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }

    fun addLeftData(){
        data.value?.leftData
//        data.value = leftData + 1;
    }

}