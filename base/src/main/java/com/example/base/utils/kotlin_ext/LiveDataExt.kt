package com.getremark.base.kotlin_ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * 只监听一次数据变化然后移除监听
 * @receiver LiveData<T>
 * @param lifecycleOwner LifecycleOwner
 * @param observer Observer<T>
 */
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}