package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.GridView

import android.view.Gravity
import android.view.View
import android.widget.GridView
import android.widget.SeekBar
import com.aserbao.aserbaosandroid.AUtils.data.AViewDataUtils
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.DisplayUtils
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.*
import com.example.base.base.interfaces.IBaseRvItemInSeekBarListener
import com.example.base.base.interfaces.ISelItemListener
import com.example.base.utils.data.StaticFinalValues

class GridViewActivity : BaseRecyclerViewActivity() ,IBaseRvItemInSeekBarListener{
    val GRIDVIEWNUM = 0
    val COLUMN_WIDTH_TAG = 1
    val HORIZONTALSPACING = 2
    val NUMCOLUMNS = 3
    val VERTICALSPACING = 4

    var gridViewNum = 5;
    var columWidth = DisplayUtils.dp2px(60f)
    var horizontalSpacing = DisplayUtils.dp2px(4f)
    var verticalSpacing = DisplayUtils.dp2px(4f)
    var numColumns = 3
    var stretchMode = GridView.NO_STRETCH
    var gravity = Gravity.CENTER
    var mStrings : MutableList<String> = ArrayList()
    override fun initGetData() {
        for (i in 1..gridViewNum){
            mStrings.add("sdd")
        }
        mBaseRecyclerBean.add(BaseRecyclerBean(0,StaticFinalValues.VIEW_GRID_VIEW_ITME,
            GridViewBean(mStrings,"GridView的基本用法",columWidth,gravity,horizontalSpacing,numColumns,stretchMode,verticalSpacing)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("GridView的个数", GRIDVIEWNUM, 20, gridViewNum, this)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("ColumnWidth", COLUMN_WIDTH_TAG, DisplayUtils.dp2px(100f), columWidth, this)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("horizontalSpacing", HORIZONTALSPACING, DisplayUtils.dp2px(30f), horizontalSpacing, this)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("verticalSpacing", VERTICALSPACING, DisplayUtils.dp2px(30f), verticalSpacing, this)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSeekBarBean("numColumns", NUMCOLUMNS, 10,numColumns, this)))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSelecteBean("stretchMode",AViewDataUtils.getGridViewStretchMode(),object :ISelItemListener{
            override fun selItemClick(view: View?, position: Int, isLongClick: Boolean, selBean: SelBean) {
                stretchMode = selBean.value
                changeData()
            }
        })))
        mBaseRecyclerBean.add(BaseRecyclerBean(VHSelecteBean("Gravity",AViewDataUtils.getGravity(),object :ISelItemListener{
            override fun selItemClick(view: View?, position: Int, isLongClick: Boolean, selBean: SelBean) {
                gravity = selBean.value
                changeData()
            }
        })))
    }

    fun changeData(){
        mStrings.clear()
        for (i in 1..gridViewNum){
            mStrings.add("sdd")
        }
        var baseRecyclerBean = BaseRecyclerBean(0, StaticFinalValues.VIEW_GRID_VIEW_ITME,
            GridViewBean(mStrings, "GridView的基本用法", columWidth, gravity, horizontalSpacing, numColumns, stretchMode, verticalSpacing))
        mBaseRecyclerBean.removeAt(0);
        mBaseRecyclerBean.add(0,baseRecyclerBean);
        mCommonAdapter.notifyItemChanged(0)
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {}


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean, tag: Int) {
        when(tag){
            GRIDVIEWNUM -> gridViewNum = progress
            COLUMN_WIDTH_TAG -> columWidth = progress
            HORIZONTALSPACING -> horizontalSpacing = progress
            VERTICALSPACING -> verticalSpacing = progress
            NUMCOLUMNS -> numColumns = progress
        }
        changeData()
    }



}
