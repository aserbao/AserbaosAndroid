package com.getremark.base.kotlin_ext

fun <E> isNotEmpty(collection: Collection<E>?): Boolean {
    return (collection != null && collection.isNotEmpty())
}
