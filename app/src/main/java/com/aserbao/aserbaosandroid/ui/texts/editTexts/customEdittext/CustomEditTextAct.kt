package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.SeekBar
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.AutoFitEditText
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.AutoFitEditTextGroup
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.DisplayUtils
import com.example.base.BaseApplication
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.beans.VHSeekBarBean
import com.example.base.base.interfaces.IBaseRvItemInSeekBarListener
import com.example.base.utils.others.ASoftKeyBoardMgr

class CustomEditTextAct : BaseRecyclerViewActivity() {

    var autoFitEditText:AutoFitEditText ?= null
    val MAX_STEP_WIDTH = 100


    override fun initGetData() {
//        style/Theme.AppCompat.Light.NoActionBar
//        setTheme(R.style.NoActionBar)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//       mBaseRecyclerBean.add(BaseRecyclerBean("整个View放大缩小的做法",CustomEditTextActivity::class.java))
       mBaseRecyclerBean.add(BaseRecyclerBean("自动适配文字大小的EditText",0))
       mBaseRecyclerBean.add(BaseRecyclerBean("自动适配文字大小的EditText",1))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("GridView的个数", 0, MAX_STEP_WIDTH, 10, object :IBaseRvItemInSeekBarListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean, tag: Int) {
                if(fromUser) changeEditTextHW(Math.min(progress + 1,MAX_STEP_WIDTH))
            }
        })))
    }
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0->{
                var autoFitEditText =  AutoFitEditTextGroup(this)
                addViewHToFl(autoFitEditText,true,true,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT,false);
            }
            1 ->{
//                var autoFitEditText = EditText(this)
                autoFitEditText =  AutoFitEditText(this)


                autoFitEditText?.apply {
                maxHeight = DisplayUtils.dp2px(300f);
                    setBackgroundColor(Color.WHITE)
//                addViewHToFl(autoFitEditText,false,false,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT,false);
                    addViewHToFl(autoFitEditText,false,false,FrameLayout.LayoutParams.MATCH_PARENT,DisplayUtils.dp2px(300f),false);

                    postDelayed({
                        autoFitEditText?.requestFocus()
                        ASoftKeyBoardMgr.openKeybord(autoFitEditText,context)
                    },100)
                }

            }
        }
    }


    fun changeEditTextHW(cuurPosition:Int){
        var fl = MAX_STEP_WIDTH / cuurPosition.toFloat()
        var changeWidth = (BaseApplication.screenWidth / fl).toInt()

        Log.e(TAG, ": changeEditTextHW fl=" + fl + " changeWidth="+ changeWidth );
        autoFitEditText?.apply {
            changeSeekBar(cuurPosition/100f)
            /*var tempLayout = this.layoutParams
            tempLayout.width = changeWidth
            Log.e(TAG, ": changeEditTextHW changeWidth="+ changeWidth  );
            this.layoutParams = tempLayout*/
        }
    }



}