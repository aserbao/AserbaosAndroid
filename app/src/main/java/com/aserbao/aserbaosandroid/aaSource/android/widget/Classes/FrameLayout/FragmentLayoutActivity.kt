package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.FrameLayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class FragmentLayoutActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("添加视图",0))
        mBaseRecyclerEmptyContainer.removeAllViews()
        var linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        for (index in 0..3){
            var button = Button(this)
            button.setText(index.toString())
            linearLayout.addView(button,index)
        }
        mBaseRecyclerEmptyContainer.addView(linearLayout)
    }

}
