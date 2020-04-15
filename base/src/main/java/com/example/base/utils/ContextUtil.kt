package com.example.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources

object ContextUtil {
    @SuppressLint("StaticFieldLeak")
    @JvmStatic
    lateinit var context: Context
        private set

    @JvmStatic
    fun getResources(): Resources {
        return context.resources
    }

    fun init(context: Context) {
        ContextUtil.context = context
    }
}