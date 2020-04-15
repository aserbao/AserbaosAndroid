package com.getremark.base.kotlin_ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> observer(t) } })
}

fun <T> LifecycleOwner.observeOnce(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observeOnce(this, Observer { it?.let { t -> observer(t) } })
}
