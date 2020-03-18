package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode;

import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView.CameraRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecodeVideoShowGlSurfaceViewActivity extends AppCompatActivity implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "DecodeVideoShowGlSurfac";
    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gl_surface_view_layout);
        ButterKnife.bind(this);
        mGlSurfaceView.setEGLContextClientVersion(2);
        CameraRenderer cameraRenderer = new CameraRenderer(this,this);
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(mInputVideoFileName);
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        String rotation = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
//        cameraRenderer.setRotate(Integer.parseInt(rotation));
        Log.e(TAG, "onCreate: width =" + width + " height = "+ height + " rotation = " + rotation  );
        int rotate = 0;
//        rotate = 90;
//        rotate = 180;
//        rotate = 270;
        boolean isLandscape = false;
        if (Integer.parseInt(width) > Integer.parseInt(height)) isLandscape = true;
        cameraRenderer.setIsLandscape(isLandscape);
        cameraRenderer.setRotate(rotate);
        mGlSurfaceView.setRenderer(cameraRenderer);
//        mGlSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        mGlSurfaceView.requestRender();
    }
    public static  String mInputVideoFileName = "/storage/emulated/0/aserbao10.mp4";
    public static void newMediaCodec(SurfaceTexture surfaceTexture) {
        MediaCodecDecode mMediaCodecDecode = new MediaCodecDecode(mInputVideoFileName, new Surface(surfaceTexture));
        mMediaCodecDecode.start();
    }

}
