package com.example.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.camera.camerax.CameraXPreviewActivity

class CameraActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("CameraX的预览",CameraXPreviewActivity::class.java))
    }


}
