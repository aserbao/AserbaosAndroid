package com.getremark.base.kotlin_ext

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm"  // 2019-08-17 13:00

fun formatTime(time: Long, pattern: String = TIME_FORMAT_DEFAULT): String {
    val dateFormat = DateFormat.getInstance() as SimpleDateFormat
    dateFormat.applyPattern(pattern)
    return dateFormat.format(Date(time))
}

val currentYear = Calendar.getInstance().get(Calendar.YEAR)

val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1

val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

const val DAY_PER_WEEK = 7

const val HOUR_PER_DAY = 24

const val MINUTE_PER_HOUR = 60