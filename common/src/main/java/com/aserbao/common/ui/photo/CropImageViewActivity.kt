package com.aserbao.common.ui.photo

import android.view.View
import android.widget.ImageView
import com.aserbao.common.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/6/7
 * @project: AserbaosAndroid
 * @package: com.aserbao.common.ui.photo
 */
class CropImageViewActivity : BaseRecyclerViewActivity() {
    var cropImageView: CropImageView ?= null

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("显示裁剪图片",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("获取裁剪图片",1))
    }

    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0 -> {
                cropImageView = CropImageView(this)
                cropImageView?.apply {
                    setImageResource(R.drawable.mm_1)
                    addViewToFrameLayout(this, false, false, true)
                }
            }
            1 ->{
                var imageView = ImageView(this)
                cropImageView?.apply {
                    var cropBitmap = getCropBitmap(300, 300, true)
                    imageView.setImageBitmap(cropBitmap);
                    addViewToFrameLayout(imageView, false, false, false)
                }
            }
        }
    }
}