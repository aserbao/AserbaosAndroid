package com.aserbao.aserbaosandroid.aaSource.android.material.bottomSheetDialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.aaSource.android.material.bottomSheetDialog.simple.SimpleBottomSheetDialog
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.interfaces.IClickBackListener

class BottomSheetDialogActivity : BaseRecyclerViewActivity() {


    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("一个简单的BottomSheetDialog",0))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

        when(position){
            0 -> {
                var simpleBottomSheetDialog = SimpleBottomSheetDialog(object : IClickBackListener {
                    override fun clickItem(which: Int, view: View) {
                        addButton()
                    }
                })
                simpleBottomSheetDialog.show(supportFragmentManager,"test")
            }

        }
    }

    fun addButton(){
        var button = Button(this)
        addViewToFrameLayout(button,false,false,false)
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_dialog)
    }*/
}
