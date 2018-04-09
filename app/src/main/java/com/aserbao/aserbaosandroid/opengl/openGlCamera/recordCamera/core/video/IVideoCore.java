package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.video;

import android.graphics.SurfaceTexture;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.listener.IVideoChange;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder.MediaMuxerWrapper;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.RecordConfig;

/**
 * Created by lake on 16-5-25.
 */
public interface IVideoCore {
    int OVERWATCH_TEXTURE_ID = 10;
    boolean prepare(RecordConfig resConfig);

    void updateCamTexture(SurfaceTexture camTex);

    void startPreview(SurfaceTexture surfaceTexture, int visualWidth, int visualHeight);

    void updatePreview(int visualWidth, int visualHeight);

    void stopPreview(boolean releaseTexture);

    boolean startRecording(MediaMuxerWrapper muxer);

    boolean stopRecording();

    boolean destroy();

    void setCurrentCamera(int cameraIndex);

    void setVideoChangeListener(IVideoChange listener);
}
