package com.example.base.component

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.AnyThread
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.utils.log.ALogUtils
import com.getremark.base.kotlin_ext.isMainThread
import kotlinx.coroutines.*


open class BaseViewModel(private val app: Application) : AndroidViewModel(app) {

    protected val TAG by lazy { this.javaClass.simpleName }

    private val mainHandler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        mainHandler.removeCallbacksAndMessages(null)
    }

    fun getString(@StringRes resId: Int): String {
        return app.getString(resId)
    }

    fun getColor(@ColorRes resId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            app.getColor(resId)
        } else {
            app.resources.getColor(resId)
        }
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            app.getDrawable(resId)
        } else {
            app.resources.getDrawable(resId)
        }
    }

    protected fun launch(
        block: suspend (CoroutineScope) -> Unit,
        error: (suspend (Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: Throwable) {
                error?.invoke(e)
            }
        }
    }

    protected fun launchIO(
        block: suspend (CoroutineScope) -> Unit,
        error: (suspend (Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block.invoke(this)
            } catch (e: Throwable) {
                error?.invoke(e)
            }
        }
    }

    protected suspend fun runOnMainSync(block: suspend () -> Unit) {
        withContext(Dispatchers.Main) {
            block.invoke()
        }
    }

    protected suspend fun runSafe(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            ALogUtils.e("runSafe error")
        }
    }

    /**
     * 一般用[runOnMainSync]可以实现线程切换了，但是某些场景需要通过handler来控制程序执行逻辑，
     * 那么就可以用这个方法。
     */
    protected fun postOnMain(runnable: Runnable, delay: Long = 0) {
        mainHandler.postDelayed({
            runnable.run()
        }, delay)
    }
}

@AnyThread
inline fun <reified T> MutableLiveData<T>.set(value: T) {
    if (isMainThread()) {
        this.value = value
    } else {
        this.postValue(value)
    }
}

@AnyThread
inline fun <reified T> MutableLiveData<T>.setNext(map: (T) -> T) {
    set(map(verifyLiveDataNotEmpty()))
}

@AnyThread
inline fun <reified T> LiveData<T>.verifyLiveDataNotEmpty(): T {
    return value ?: throw NullPointerException("MutableLiveData<${T::class.java}> not contain value.")
}