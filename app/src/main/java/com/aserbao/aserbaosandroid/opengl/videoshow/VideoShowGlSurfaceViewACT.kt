package com.aserbao.aserbaosandroid.opengl.videoshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity


/**
 * @author: aserbao
 * @date:2020/8/15 11:32 AM
 * @package:com.aserbao.aserbaosandroid.opengl.videoshow
 * @describle:本地视频OpenGl显示类
 */
class VideoShowGlSurfaceViewACT : BaseRecyclerViewActivity() {
    override fun initGetData() {
        addLayoutToFrameLayout(R.layout.opengl_video_show_glsv,true)
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }


}