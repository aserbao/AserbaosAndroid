package com.aserbao.aserbaosandroid.opengl.videoshow

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.doubleshow.CameraPreviewRender
import com.example.base.base.BaseRecyclerViewActivity
import kotlinx.android.synthetic.main.activity_camera2_glsurface_view.view.*


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

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {}
}