package com.example.camera.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.arouter.RouterConfig
import com.example.camera.R

@Route(path = RouterConfig.JUMP_TO_CAMERA_ACTIVITY2)
class Camera2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)
    }
}