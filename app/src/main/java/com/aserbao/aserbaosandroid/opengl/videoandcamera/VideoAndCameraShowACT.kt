package com.aserbao.aserbaosandroid.opengl.videoandcamera

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.doubleshow.CameraPreviewRender
import com.aserbao.aserbaosandroid.databinding.OpenglVideoAndCameraShowGlsvBinding
import com.example.base.base.BaseRecyclerViewActivity
import kotlinx.android.synthetic.main.activity_camera2_glsurface_view.view.*
import kotlinx.android.synthetic.main.opengl_video_and_camera_show_glsv.*
import kotlinx.android.synthetic.main.person_list_content.*
import java.util.zip.Inflater

/**
 * @author: aserbao
 * @date:2020/8/15 7:13 PM
 * @package:com.aserbao.aserbaosandroid.opengl.videoandcamera
 * @describle:视频和相机同时显示
 */
class VideoAndCameraShowACT : BaseRecyclerViewActivity() {
    lateinit var bindingRoot:View
    override fun initGetData() {
        bindingRoot = OpenglVideoAndCameraShowGlsvBinding.inflate(LayoutInflater.from(this)).root
        addViewToFrameLayout(bindingRoot,true,true,true)
        bindingRoot.apply {
            cameraGLSV?.apply {
                setEGLContextClientVersion(2) //在setRenderer()方法前调用此方法
                setRenderer(CameraPreviewRender(this@VideoAndCameraShowACT, this, "0"))
                renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
            }
        }
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {}

}