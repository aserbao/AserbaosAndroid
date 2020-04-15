package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;


/**
 * 功能:
 * @author aserbao
 * @date : On 2019-12-14 17:53
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView
 */
public class ShowDecodeGlSurfaceView extends GLSurfaceView implements SurfaceTexture.OnFrameAvailableListener{

    private final CameraRenderer cameraRenderer;

    public ShowDecodeGlSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        cameraRenderer = new CameraRenderer(context,this);
        setRenderer(cameraRenderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
         requestRender();
    }
}
