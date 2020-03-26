package com.aserbao.aserbaosandroid.aaThird.jetpack.databinding

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels.SimpleViewModel.HeadType

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/26
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.databinding
 */
object SimpleAdapter{
    @BindingAdapter("app:detailUserHead")
    @JvmStatic fun detailUserHead(view: ImageView, headtype: HeadType){
        var drawablePopularity = getDrawablePopularity(headtype, view.context)
        view.setImageDrawable(drawablePopularity)
//        view.setImageResource(getDrawablePopularitys(headtype)!!)
    }

    private fun getDrawablePopularity(headtype: HeadType, context: Context): Drawable? {
        return when (headtype) {
            HeadType.SAD -> {
                ContextCompat.getDrawable(context, R.drawable.emoji_16)
            }
            HeadType.SMILE -> {
                ContextCompat.getDrawable(context, R.drawable.emoji_09)
            }
            HeadType.HAPPY -> {
                ContextCompat.getDrawable(context, R.drawable.emoji_00)
            }
        }
    }

    private fun getDrawablePopularitys(headtype: HeadType): Int? {
        return when (headtype) {
            HeadType.SAD -> { R.drawable.emoji_16 }
            HeadType.SMILE -> {R.drawable.emoji_09 }
            HeadType.HAPPY -> {R.drawable.emoji_00 }
        }
    }
}