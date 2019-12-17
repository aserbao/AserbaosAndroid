package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode;

import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView.CameraRenderer;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView.ShowDecodeGlSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

public class DecodeVideoShowGlSurfaceViewActivity extends AppCompatActivity implements SurfaceTexture.OnFrameAvailableListener {

    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gl_surface_view_layout);
        ButterKnife.bind(this);
        mGlSurfaceView.setEGLContextClientVersion(2);
        CameraRenderer cameraRenderer = new CameraRenderer(this,this);
        mGlSurfaceView.setRenderer(cameraRenderer);
//        mGlSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        mGlSurfaceView.requestRender();
    }

    public static void newMediaCodec(SurfaceTexture surfaceTexture) {
        String videoFileName = "/storage/emulated/0/720aserbao5.mp4";
        MediaCodecDecode mMediaCodecDecode = new MediaCodecDecode(videoFileName, new Surface(surfaceTexture));
        mMediaCodecDecode.start();
    }

}
