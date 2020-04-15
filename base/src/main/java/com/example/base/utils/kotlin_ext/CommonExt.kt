package com.getremark.base.kotlin_ext

import android.app.ActivityManager
import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Checkable
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.base.BuildConfig
import com.example.base.utils.ContextUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.setPreVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

//兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

@Deprecated("不好理解，用dip()")
fun Int.toPxFloat(): Float {
    val density = ContextUtil.getResources().displayMetrics.density
    return Math.round(this * density).toFloat()
}

@Deprecated("不好理解，用dip()")
fun Float.toPx(): Int {
    val density = ContextUtil.getResources().displayMetrics.density
    return Math.round(this * density)
}

fun isLocationServiceEnable(): Boolean {
    val locationManager =
        ContextUtil.context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    // getting GPS uploadStatus
    val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    // getting network uploadStatus
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    return isGPSEnabled || isNetworkEnabled
}

// Transform simple object to String with Gson
inline fun <reified T : Any> T.toPrettyJson(): String = Gson().toJson(this, T::class.java)

// Transform String Json to Object
inline fun <reified T : Any> String.fromPrettyJson(): T = Gson().fromJson(this, T::class.java)

// Transform String List Json to Object
inline fun <reified T : Any> String.fromPrettyJsonList(): MutableList<T> = when (this.isNotEmpty()) {
    true -> Gson().fromJson(this, object : TypeToken<MutableList<T>>() {}.type)
    false -> mutableListOf()
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.runOnNonNull(block: (T) -> Unit) {
    block.invoke(this as T)
}

var sCircleOutlineProvider: ViewOutlineProvider? = null

fun <T : View> T.circleOutline(): T {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        clipToOutline = true
        if (sCircleOutlineProvider == null) {
            sCircleOutlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    view?.run {
                        outline?.setRoundRect(0, 0, width, height, width.toFloat() / 2)
                    }
                }
            }
        }
        outlineProvider = sCircleOutlineProvider
    }
    return this
}


fun isDebug(): Boolean {
    return BuildConfig.DEBUG
}


@ColorInt
fun parseColorAlpha(colorStr: String, @FloatRange(from = 0.0, to = 1.0) alpha: Float = 0.0f): Int {
    val colorInt = Color.parseColor(colorStr)
    if (alpha != -1f) {
        val red = Color.red(colorInt)
        val green = Color.green(colorInt)
        val blue = Color.blue(colorInt)
        return Color.argb((alpha * 0xff).toInt(), red, green, blue)
    }
    return colorInt
}

//@ExperimentalContracts
//fun Any?.isNotNull(): Boolean {
//    contract {
//        returns(true) implies (this@isNotNull != null)
//    }
//    return this != null
//}

const val HTTP_TIME_OUT = 10000L

fun isMainThread() = Thread.currentThread() == Looper.getMainLooper().thread

fun runOnMainThread(block: () -> Unit) {
    if (isMainThread()) {
        block()
    } else {
        mainHandler.post { block() }
    }
}

val isMainProcess: Boolean
    get() = TextUtils.equals(
        ContextUtil.context!!.applicationContext.packageName,
        currentProcessName
    )

/**
 * 获取当前进程名称
 *
 * @return
 */
@get:NonNull
val currentProcessName: String
    get() {
        val pid: Int = android.os.Process.myPid()
        val manager: ActivityManager =
            ContextUtil.context!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process: ActivityManager.RunningAppProcessInfo in Objects.requireNonNull(manager).getRunningAppProcesses()) {
            if (process.pid === pid) {
                return process.processName
            }
        }
        return ""
    }

fun <T> MutableLiveData<T>.default(value: T): MutableLiveData<T> {
    this.value = value
    return this
}

fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notifyDataSetChangedInMain() {
    if (isMainThread()) {
        this.notifyDataSetChanged()
    } else {
        Handler(Looper.getMainLooper()).post {
            this.notifyDataSetChanged()
        }
    }
}

val mainHandler = Handler(Looper.getMainLooper())

fun threadName() = Thread.currentThread().name