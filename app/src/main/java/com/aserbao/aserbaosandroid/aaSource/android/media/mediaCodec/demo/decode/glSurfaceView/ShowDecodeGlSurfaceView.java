package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.aserbao.aserbaosandroid.AUtils.utils.BufferUtil;
import com.aserbao.aserbaosandroid.AUtils.utils.ShaderUtils;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleOne.CameraOneView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

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
