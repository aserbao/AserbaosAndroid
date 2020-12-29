package com.aserbao.jetpack.architecture.liveData

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

/*
* 作用：
* @author aserbao
* @date: on 2020/4/3
* @project: AserbaosAndroid
* @package: com.aserbao.jetpack.architecture.liveData
*/
class UseMediatorLiveData : ViewModel() {
    var isChangeText = MutableLiveData(false)
    var isChangeSize = MutableLiveData(false)

    var isChange = MediatorLiveData<Boolean>()
    init {
        isChange.value = false
        isChange.addSource(isChangeText, Observer { isChange.value = it } )
        isChange.addSource(isChangeSize, Observer { isChange.value = it } )
    }
}