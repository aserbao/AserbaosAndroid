package com.aserbao.aserbaosandroid.ui.texts.textViews.font

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import java.lang.reflect.Type

class TextFontActivity : BaseRecyclerViewActivity() {
    var defaultContent:String = "测试字体 \\n this is a very good android project"
    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("沐瑶软笔手写体",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("汉体",1))

    }
    override fun itemClickBack(view: View, position: Int, isLongClick: Boolean, comeFrom: Int) {
        var tf:Typeface ?= null
        when(position){
            0 ->{
                tf = Typeface.createFromAsset(assets, "fonts/yao_soft_pen_handwriting.ttf")
            }
            1 ->{
                tf = Typeface.createFromAsset(assets, "fonts/SourceHanSerifCN-Heavy.otf")
            }
        }
        tf?.apply {
            addTextView(this)
        }
    }


    fun addTextView(tf: Typeface){
        var textView = TextView(this)
        textView.textSize = 30f
        textView.setTypeface(tf)
        textView.text  = defaultContent
        addViewToFrameLayout(textView,false,false,false)
    }
}