package com.aserbao.aserbaosandroid.Cameratest;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneCameraActivity extends AppCompatActivity {

    @BindView(R.id.auto_fit_texture_view)
    AutoFitTextureView mAutoFitTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_camera);
        ButterKnife.bind(this);
        mAutoFitTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {


        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            openCamera(width,height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private void openCamera(int width, int height) {
        setUpCameraOutputs(width,height);
    }

    /**设置相机的输出参数
     * @param width
     * @param height
     */
    private void setUpCameraOutputs(int width, int height) {

    }
}
