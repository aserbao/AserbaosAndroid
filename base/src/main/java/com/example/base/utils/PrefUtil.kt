package com.example.base.utils

import android.preference.PreferenceManager
import android.text.TextUtils
import com.example.base.utils.kotlin_ext.getContext

/**
 * @author: aserbao
 * @date:2020/11/25 9:41 AM
 * @package:com.example.base.utils
 * @describle:
 */
object PrefUtil {

    /**
     * 以下兼容Java代码，Kotlin中使用[DEFAULT_PREF_DELEGATE]即可
     */

    @JvmStatic
    fun put(key: String, value: String) {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        sp.edit().putString(key, value).apply()
    }

    @JvmStatic
    fun put(key: String, value: Boolean) {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        sp.edit().putBoolean(key, value).apply()
    }

    @JvmStatic
    fun put(key: String, value: Long) {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        sp.edit().putLong(key, value).apply()
    }

    @JvmStatic
    fun put(key: String, value: Int) {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        sp.edit().putInt(key, value).apply()
    }

    @JvmStatic
    fun put(key: String, value: Float) {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        sp.edit().putFloat(key, value).apply()
    }

    @JvmStatic
    fun getString(key: String, defaultValue: String? = null): String {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        return sp.getString(key, defaultValue) ?: ""
    }

    @JvmStatic
    fun getLong(key: String): Long {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        return sp.getLong(key, -1L)
    }

    @JvmStatic
    fun getInt(key: String, defaultValue: Int = -1): Int {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        return sp.getInt(key, defaultValue)
    }

    @JvmStatic
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        return sp.getBoolean(key, defaultValue)
    }

    @JvmStatic
    fun getFloat(key: String, defaultValue: Float = 0F): Float {
        val sp = PreferenceManager.getDefaultSharedPreferences(getContext())
        return sp.getFloat(key, defaultValue)
    }

}

