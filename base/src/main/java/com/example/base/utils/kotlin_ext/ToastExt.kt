package com.getremark.base.kotlin_ext

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

fun Context.toast(value: String) = toast { value }

fun Fragment.toast(value: String) = context?.toast { value }

inline fun Context.toast(crossinline value: () -> String) =
    runOnMainThread { Toast.makeText(this, value(), Toast.LENGTH_SHORT).show() }

