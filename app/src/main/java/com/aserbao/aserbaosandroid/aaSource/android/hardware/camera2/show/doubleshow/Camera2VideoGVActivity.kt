package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.show.doubleshow

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aserbao.aserbaosandroid.databinding.ActivityCamera2GlsurfaceViewBinding
import kotlinx.android.synthetic.main.activity_camera2_glsurface_view.view.*

/**
 * @author: aserbao
 * @date:2020/8/14 6:02 PM
 * @package:
 * @describle: 开启前后置摄像头同时预览,小米手机可以正常运行，华为机型无法正常同时预览
 * 原因：只有支持三路及以上的Camera才支持同时预览，系统默认是两路Camera。
 */
class Camera2VideoGVActivity : AppCompatActivity(){
    lateinit var bindRoot: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflate = ActivityCamera2GlsurfaceViewBinding.inflate(LayoutInflater.from(this))
        bindRoot = inflate.root
        setContentView(bindRoot)
        initView()
    }
    private val mFrontCameraId = "1"
    private val mBackCameraId = "0"
    private fun initView() {
        bindRoot.apply {
            glSurfaceView?.apply {
                setEGLContextClientVersion(2) //在setRenderer()方法前调用此方法
                setRenderer(CameraFontPreviewRender(this@Camera2VideoGVActivity,this,mFrontCameraId))
                renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
            }

            glSurfaceView2?.apply {
                setEGLContextClientVersion(2) //在setRenderer()方法前调用此方法
                setRenderer(CameraFontPreviewRender(this@Camera2VideoGVActivity,this,mBackCameraId))
                renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
            }

        }
    }


}