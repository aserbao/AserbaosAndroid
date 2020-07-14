package com.aserbao.aserbaosandroid.ui.texts.textViews.custom

import android.view.View
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/*
 * 作用：自定义TextView
 * @author aserbao
 * @date: on 2020/6/26
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.textViews.custom
 */
internal class CustomTextViewActivity : BaseRecyclerViewActivity() {
    override fun initGetData() {
        addLayoutToFrameLayout(R.layout.custom_text_view_layout,false)
    }
    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {}
}