package com.aserbao.common.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.common.ui.photo.CropImageViewActivity
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

class CommonUIActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("photo",CropImageViewActivity::class.java))
    }

}
