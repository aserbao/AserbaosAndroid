package com.aserbao.aserbaosandroid.media.mediacodec.mcRecordVideo;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;

import com.aserbao.aserbaosandroid.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MCRecordVideoActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener,Camera.PreviewCallback{

    @BindView(R.id.mc_record_texure_view)
    TextureView mMcRecordTexureView;
    public Camera mCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcrecord_video);
        ButterKnife.bind(this);
        mMcRecordTexureView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        try {
            mCamera = Camera.open(0);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewTexture(mMcRecordTexureView.getSurfaceTexture());
            mCamera.setPreviewCallback(this);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }
}
