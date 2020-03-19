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
class SharedViewModels : ViewModel(){
    // Create a LiveData with a String
    private var currentName: MutableLiveData<String> ?= null
    private var currentRightName: MutableLiveData<String> ?= null
    fun getCurrentName(): MutableLiveData<String> {
        if (currentName == null) {
            currentName = MutableLiveData()
        }
        return currentName as MutableLiveData<String>
    }

    fun getCurrentRightName(): MutableLiveData<String> {
        if (currentRightName == null) {
            currentRightName = MutableLiveData()
        }
        return currentRightName as MutableLiveData<String>
    }

    fun changeString(s: String) {
        currentName!!.value = s
    }

    fun changeRight(s: String) {
        currentRightName!!.value = s
    }
}