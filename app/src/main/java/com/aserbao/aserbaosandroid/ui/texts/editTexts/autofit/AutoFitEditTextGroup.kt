package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.databinding.AutoFitEditTextGroupBinding
import com.aserbao.aserbaosandroid.ui.customView.seekbar.rangeSeekBar.OnRangeChangedListener
import com.aserbao.aserbaosandroid.ui.customView.seekbar.rangeSeekBar.RangeSeekBar
import com.aserbao.aserbaosandroid.ui.customView.seekbar.rangeSeekBar.VerticalRangeSeekBar
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText.AutoEditText
import com.example.base.utils.screen.DisplayUtil
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.auto_fit_edit_text_group.view.*

/*
 * 作用：仿Instagram的文本编辑
 * @author aserbao
 * @date: on 2020/11/4
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit
 */
class AutoFitEditTextGroup(context: Context,var attrs: AttributeSet ?= null,var defStyleAttr: Int = 0,var defStyleRes: Int = 0) : FrameLayout(context,attrs,defStyleAttr,defStyleRes) {

    init {
        initView()
    }

    var seekBar: VerticalRangeSeekBar? = null
    var autoFitEditText: AutoEditText? = null
    lateinit var rootViewBind :View

    private fun initView() {
        rootViewBind = AutoFitEditTextGroupBinding.inflate(LayoutInflater.from(context)).root
        addView(rootViewBind)
//        autoFitSeekBar.setProgress(10f)
        autoFitEditText?.apply {
            val layoutParams = getLayoutParams()
            layoutParams.width = DisplayUtil.getScreenWidth(context)
            setLayoutParams(layoutParams)
        }
        initViewEvent()
    }

    fun initViewEvent(){
        rootViewBind?.apply {
            alignIv.singleClick {
                var gravity = autoFitEditText.gravity
                when(gravity){
                    Gravity.LEFT,
                    Gravity.LEFT or Gravity.CENTER_VERTICAL -> {
                        autoFitEditText.gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
                        alignIv.setImageResource(R.drawable.align_center)
                    }
                    Gravity.RIGHT,
                    Gravity.RIGHT or Gravity.CENTER_VERTICAL -> {
                        autoFitEditText.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                        alignIv.setImageResource(R.drawable.align_left)
                    }
                    else ->{
                        autoFitEditText.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                        alignIv.setImageResource(R.drawable.align_right)
                    }
                }
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_yellow) )
            }
            colorPickerIv.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_yellow) )
            }
            color_selector_yellow.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_yellow) )
            }
            color_selector_cyan.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_cyan) )
            }
            color_selector_red.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_red) )
            }
            color_selector_grey.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_grey) )
            }
            color_selector_black.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_black) )
            }
            color_selector_pink.singleClick {
                autoFitEditText.setTextColor(getResources().getColor(R.color.paint_color_pink) )
            }

            autoFitSeekBar.maxProgress = 100f;
            autoFitSeekBar.setProgress(50f)
            autoFitSeekBar.setOnRangeChangedListener(object :OnRangeChangedListener{
                override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

                }

                override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                    Log.d("ATAG", "null() called leftValue="+leftValue+ " rightValu="+ rightValue + " isFromUser="+ isFromUser);
                    autoFitEditText.setTextFont(leftValue)
                }

                override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
                }

            })


            bgIv.singleClick {
                var selected = bgIv.isSelected
                if (selected) {
                    bgIv.setImageResource(R.drawable.fontsize_white)
                    autoFitEditText.setBackgroundColorSpan(Color.TRANSPARENT)
                }else{
                    bgIv.setImageResource(R.drawable.fontsize_black)
                    autoFitEditText.setBackgroundColorSpan(Color.BLACK)
                }
                bgIv.isSelected = !selected
                autoFitEditText.setForceRefresh()
            }
        }
    }


    private fun changeSpan(content: String) {
        val spannableString = SpannableString(content)
        val backgroundColorSpan = BackgroundColorSpan(Color.YELLOW)
        val start = 0
        val end = content.length
        spannableString.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //        ColorSpan colorSpan = new ColorSpan(Color.RED,Color.BLUE);
//        spannableString.setSpan(spannableString,start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        autoFitEditText!!.setText(spannableString)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
    }
}