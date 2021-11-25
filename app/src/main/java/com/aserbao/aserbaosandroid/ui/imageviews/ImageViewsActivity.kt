package com.aserbao.aserbaosandroid.ui.imageviews

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.ui.imageviews.custom.ShadowRoundImageView
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.utils.data.ASourceUtil
import com.example.base.utils.screen.DisplayUtil

class ImageViewsActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
//        mBaseRecyclerBean.add(new BaseRecyclerBean("心形ImageView",520));
        mBaseRecyclerBean.add(BaseRecyclerBean("带阴影的ImageView", 1))
        mBaseRecyclerBean.add(BaseRecyclerBean("只显示图片黑白色的ImageView", 2))
        mBaseRecyclerBean.add(BaseRecyclerBean("动态修改tint", 3))
    }

    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mBaseRecyclerEmptyContainer.removeAllViews()
        val width = DisplayUtil.dp2px(62)
        val height = DisplayUtil.dp2px(58)
        val layoutParams = FrameLayout.LayoutParams(width, height, Gravity.CENTER)
        mBaseRecyclerEmptyContainer.setBackgroundColor(Color.TRANSPARENT)
        when (position) {
            520 -> {
            }
            1 -> {
                val shadowRoundImageView = ShadowRoundImageView(this)
                shadowRoundImageView.setImageResource(R.drawable.mm_1)
                shadowRoundImageView.layoutParams = layoutParams
                mBaseRecyclerEmptyContainer.setBackgroundColor(Color.WHITE)
                mBaseRecyclerEmptyContainer.addView(shadowRoundImageView)
            }
            2 ->{
                var imageView = ImageView(this)
                imageView.setImageResource(ASourceUtil.getRandomImageId())
                mBaseRecyclerEmptyContainer.addView(imageView)
                onlyBlackAndWhite(imageView)
            }
            3->{
                var mImageView = ImageView(this)
                mImageView.setImageURI(Uri.parse("/storage/emulated/0/DCIM/Camera/IMG_20210908_180325.jpg"))
                mBaseRecyclerEmptyContainer.addView(mImageView)
            }
        }
    }

    fun createBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_4444)
        bitmap.eraseColor(Color.parseColor("#ff0000")) // 填充颜色
        return bitmap
    }

    /**
     * 通过修改图片的饱和度来实现黑白图片ImageView
     */
    fun onlyBlackAndWhite(imageView:ImageView){
        val cm = ColorMatrix()
        cm.setSaturation(0f) // 设置饱和度:0为纯黑白，饱和度为0；1为饱和度为100，即原图；

        val grayColorFilter = ColorMatrixColorFilter(cm)
        imageView.setColorFilter(grayColorFilter)
    }
}