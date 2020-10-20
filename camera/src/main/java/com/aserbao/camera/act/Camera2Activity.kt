package com.aserbao.camera.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.aserbao.camera.IHandleCameraListener
import com.example.base.arouter.RouterConfig
import com.aserbao.camera.R
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.activity_camera2.*
import java.lang.Exception

@Route(path = RouterConfig.JUMP_TO_CAMERA_ACTIVITY2)
class Camera2Activity : AppCompatActivity(),IHandleCameraListener {
    private val TAG = "Camera2Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)
        initView()
    }

    private fun initView() {
        btn1.singleClick {
//            camera2View.startRecord("test",this)
            camera2View.capturePic("test",this)
        }
        btn2.singleClick {
            camera2View.switchCamera()
        }
    }

    override fun capturePic(picPath: String?) {
        Toast.makeText(this, picPath, Toast.LENGTH_SHORT).show()
        Log.e(TAG, ": capturePic" + picPath );
    }

    override fun error(e: Exception?) {
        Log.e(TAG, ": error" + e.toString() );
    }

    override fun videoComplete(videoPath: String?) {
        Log.e(TAG, ": videoComplete" + videoPath );
    }
}