package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraGlSurfaceShowActivity extends AppCompatActivity {

    public static Camera camera;
    @BindView(R.id.camera_glsurface_view)
    GLSurfaceView mCameraGlsurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gl_surface_show);
        ButterKnife.bind(this);
        mCameraGlsurfaceView.setRenderer(new MyRender());
        mCameraGlsurfaceView.setEGLContextClientVersion(2);
    }

    @OnClick(R.id.btn_gl_surface_view_animator)
    public void onViewClicked() {
    }

    public static class MyRender implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            camera = Camera.open(1);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }
}
