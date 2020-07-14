package com.aserbao.common.pop

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.*
import android.widget.PopupWindow
import androidx.annotation.CallSuper
import com.aserbao.common.R
import com.aserbao.common.ui.WheelView
import kotlinx.android.synthetic.main.pop_sel_size_layout.view.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/14
 * @project: AserbaosAndroid
 * @package: com.aserbao.common.pop
 */

public class PopSelSize(var mContext: Context) : PopupWindow.OnDismissListener {
    lateinit var mCuurPoupWindow: PopupWindow
    lateinit var rootView: View
    lateinit var listener:IPopSelListener
    lateinit var sizeList:MutableList<String>
    var selSize:String = "M";

    @CallSuper
    open fun showEditPop(lis:IPopSelListener,selSize:String,sizeList:MutableList<String>) {
        var bind = LayoutInflater.from(mContext).inflate(R.layout.pop_sel_size_layout, null)
        this.sizeList = sizeList
        this.selSize = selSize
        listener = lis
        rootView = bind
        mCuurPoupWindow = PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mCuurPoupWindow.isOutsideTouchable = true
        mCuurPoupWindow.isFocusable = true
        mCuurPoupWindow.setOnDismissListener(this)
        mCuurPoupWindow.setAnimationStyle(R.style.pop_bottom_to_center_anim_style)
        mCuurPoupWindow.setBackgroundDrawable(BitmapDrawable())
        mCuurPoupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mCuurPoupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mCuurPoupWindow.showAtLocation(rootView.rootView, Gravity.BOTTOM, 0,0)
        initView()
    }

    open fun initView() {
        rootView.apply {
            wheelView.setOffset(2)
            wheelView.setItems(sizeList)
            var indexOf = sizeList.indexOf(selSize)
            if(indexOf > 0) wheelView.setSeletion(indexOf)
            wheelView.setOnWheelViewListener(object : WheelView.OnWheelViewListener() {
                override fun onSelected(selectedIndex: Int, item: String) {
                    selSize = item
                }
            })


        }
    }


    override fun onDismiss() {
    }

    open interface IPopSelListener{
        fun selSize(size:String);
    }
}