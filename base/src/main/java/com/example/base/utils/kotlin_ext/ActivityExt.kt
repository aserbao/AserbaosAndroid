package com.getremark.base.kotlin_ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils

/**
 * 修改状态栏颜色，会相应修改字体的颜色
 */
fun Activity.setStatusBarColorKtRes(@ColorRes colorRes: Int) {
    setStatusBarColorKt(ContextCompat.getColor(this, colorRes))
}

/**
 * 修改状态栏颜色，会相应修改字体的颜色
 */
fun Activity.setStatusBarColorKt(@ColorInt color: Int) {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        return
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = color
    } else {
        //透明化状态栏
        setStatusBarTranslucent()
        //添加一个与状态栏等高的view到布局中
        val contentView = findViewById<ViewGroup>(android.R.id.content)
        val childView = contentView.getChildAt(0)
        if (childView != null && childView is LinearLayout) {
            //偏移内容布局
            (childView.layoutParams as FrameLayout.LayoutParams).topMargin = getStatusBarHeight()
            //添加状态栏布局
            val statusView = View(this)
            statusView.setBackgroundColor(color)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
            contentView.addView(statusView, params)
        }
    }

    // 如果亮色，设置状态栏文字为黑色
    if (isLightColor(color)) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}

/**
 * 透明化状态栏。4.4以下无效
 *
 * @param activity Activity
 */
fun Activity.setStatusBarTranslucent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val window = window
        val lp = window.attributes
        lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        window.attributes = lp
    }
}

/**
 * 获取状态栏的高度
 *
 * @param context Context
 * @return 状态栏的高度
 */
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resId = this.resources.getIdentifier(
        "status_bar_height",
        "dimen", "android"
    )
    if (resId > 0) {
        result = this.resources.getDimensionPixelSize(resId)
    }
    return result
}

/**
 * 判断颜色是不是亮色
 *
 * @param color
 * @return
 * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
 */
private fun isLightColor(@ColorInt color: Int): Boolean {
    return ColorUtils.calculateLuminance(color) >= 0.5
}

fun Context.inflate(layoutId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)
}

fun Context.getColorCompat(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun View.inflate(layoutId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, parent, attachToRoot)
}

fun Window.setAlpha(alpha: Float) {
    this.attributes?.apply {
        this.alpha = alpha
    }.also { this.attributes = it }
}

//fun Activity.hideSoftInput(view: View? = null) {
//    if (view != null) {
//        LCIMSoftInputUtils.hideSoftInput(this, view)
//    } else {
//        LCIMSoftInputUtils.hideSoftInput(this, this.findViewById(android.R.id.content))
//    }
//}

fun Context.startActivitySafe(intent: Intent, requestCode: Int = -1) {
    if (intent.resolveActivity(packageManager) != null) {
        if (this is Activity) {
            if (requestCode != -1) {
                startActivityForResult(intent, requestCode)
            } else {
                startActivity(intent)
            }
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}