package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

public class CameraShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_show);
    }

    public void btn_camera_surface_view(View view) {
        startActivity(new Intent(this,CameraSurfaceViewShowActivity.class));
    }
    public void btn_camera_texture_view(View view) {
        startActivity(new Intent(this,CameraTextureViewActivity.class));
    }
}
