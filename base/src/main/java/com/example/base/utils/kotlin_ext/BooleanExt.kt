package com.getremark.base.kotlin_ext

sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> {
    return if (this) {
        WithData(block())
    } else {
        Otherwise
    }
}

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T> {
    return if (this) {
        Otherwise
    } else {
        WithData(block())
    }
}

inline fun <T> BooleanExt<T>.otherWise(block: () -> T): T {
    return when (this) {
        is Otherwise -> {
            block()
        }
        is WithData -> {
            this.data
        }
    }
}