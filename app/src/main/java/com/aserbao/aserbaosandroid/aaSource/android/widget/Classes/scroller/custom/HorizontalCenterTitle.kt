package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.scroller.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.databinding.ScrollerHorizontalTitleLayoutBinding
import kotlinx.android.synthetic.main.scroller_horizontal_title_layout.view.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/4/21
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.scroller.custom
 */
class HorizontalCenterTitle: FrameLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.scroller_horizontal_title_layout,this,true)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("HorizontalCenterTitle", "onTouchEvent" + "[event] =" + event.action)
        return super.onTouchEvent(event)
    }

}