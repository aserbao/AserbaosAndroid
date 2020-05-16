package com.aserbao.aserbaosandroid.aaSource.android.view.Choreographer

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.Choreographer
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class ChoreographerActivity():BaseRecyclerViewActivity(), Choreographer.FrameCallback {

    override fun initGetData() {
        Choreographer.getInstance().postFrameCallback(this)
    }


    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        mBaseRecyclerTv.invalidate()
    }

    override fun doFrame(frameTimeNanos: Long) {
        mBaseRecyclerTv.setText("doFrame.values = " + frameTimeNanos.toString())
        Choreographer.getInstance().postFrameCallback(this)
    }

}