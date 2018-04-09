package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.client;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.CameraHelper;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.listener.IVideoChange;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.video.IVideoCore;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.video.VideoCore;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder.MediaMuxerWrapper;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.hardvideofilter.BaseHardVideoFilter;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.MediaMakerConfig;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.RecordConfig;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model.Size;

import java.io.IOException;
import java.util.List;

/**
 *
 * Created by lake on 16-5-24.
 */
public class VideoClient {
    private static final String TAG = "VideoClient";
    MediaMakerConfig mediaMakerConfig;
    private final Object syncOp = new Object();
    private Camera camera;
    private SurfaceTexture camTexture;
    private int cameraNum;
    private int currentCameraIndex;
    private boolean mIsFrontCamera = false;
    private IVideoCore videoCore;
    private boolean isStreaming = false;
    private boolean isRecording = false;
    private boolean isPreviewing = false;

    public VideoClient(Context context, MediaMakerConfig parameters) {
        mediaMakerConfig = parameters;
        cameraNum = Camera.getNumberOfCameras();
        currentCameraIndex = CameraInfo.CAMERA_FACING_BACK;
        mIsFrontCamera = false;
        isStreaming = false;
        isRecording = false;
        isPreviewing = false;
    }

    public boolean prepare(RecordConfig config) {
        synchronized (syncOp) {
            if ((cameraNum - 1) >= config.getDefaultCamera()) {
                currentCameraIndex = config.getDefaultCamera();
                mIsFrontCamera = currentCameraIndex == CameraInfo.CAMERA_FACING_FRONT;
            }
            if (null == (camera = createCamera(currentCameraIndex))) {
                Log.e("","can not open camera");
                return false;
            }
            Camera.Parameters parameters = camera.getParameters();
            CameraHelper.selectCameraPreviewWH(parameters, mediaMakerConfig, config.getTargetVideoSize());
            CameraHelper.selectCameraFpsRange(parameters, mediaMakerConfig);
            if (config.getVideoFPS() > mediaMakerConfig.previewMaxFps / 1000) {
                mediaMakerConfig.videoFPS = mediaMakerConfig.previewMaxFps / 1000;
            } else {
                mediaMakerConfig.videoFPS = config.getVideoFPS();
            }
            resoveResolution(mediaMakerConfig, config.getTargetVideoSize());
            if (!CameraHelper.selectCameraColorFormat(parameters, mediaMakerConfig)) {
                Log.e("","CameraHelper.selectCameraColorFormat,Failed");
                mediaMakerConfig.dump();
                return false;
            }
            if (!CameraHelper.configCamera(camera, mediaMakerConfig)) {
                Log.e("","CameraHelper.configCamera,Failed");
                mediaMakerConfig.dump();
                return false;
            }
            videoCore = new VideoCore(mediaMakerConfig);
            if (!videoCore.prepare(config)) {
                return false;
            }
            videoCore.setCurrentCamera(currentCameraIndex);
            prepareVideo();
            return true;
        }
    }

    private Camera createCamera(int cameraId) {
        try {
            camera = Camera.open(cameraId);
            camera.setDisplayOrientation(0);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return camera;
    }

    private boolean prepareVideo() {
        return true;
    }

    private boolean startVideo() {
        camTexture = new SurfaceTexture(IVideoCore.OVERWATCH_TEXTURE_ID);
        camTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                synchronized (syncOp) {
                    if (videoCore != null) {
                        ((VideoCore) videoCore).onFrameAvailable();
                    }
                }
            }
        });
        try {
            camera.setPreviewTexture(camTexture);
        } catch (IOException e) {
            e.printStackTrace();
            camera.release();
            return false;
        }
        camera.startPreview();
        return true;
    }

    public boolean startPreview(SurfaceTexture surfaceTexture, int visualWidth, int visualHeight) {
        synchronized (syncOp) {
            if (!isWorking() && !isPreviewing) {
                if (!startVideo()) {
                    mediaMakerConfig.dump();
                    Log.e("","VideoClient,start(),failed");
                    return false;
                }
                videoCore.updateCamTexture(camTexture);
            }
            videoCore.startPreview(surfaceTexture, visualWidth, visualHeight);
            isPreviewing = true;
            return true;
        }
    }

    public void updatePreview(int visualWidth, int visualHeight) {
        videoCore.updatePreview(visualWidth, visualHeight);
    }

    public boolean stopPreview(boolean releaseTexture) {
        synchronized (syncOp) {
            if (isPreviewing) {
                videoCore.stopPreview(releaseTexture);
                if (!isWorking()) {
                    camera.stopPreview();
                    videoCore.updateCamTexture(null);
                    camTexture.release();
                }
            }
            isPreviewing = false;
            return true;
        }
    }

    public boolean startRecording(MediaMuxerWrapper muxer) {
        synchronized (syncOp) {
            if (!isWorking() && !isPreviewing) {
                if (!startVideo()) {
                    mediaMakerConfig.dump();
                    Log.e("","VideoClient,start(),failed");
                    return false;
                }
                videoCore.updateCamTexture(camTexture);
            }
            videoCore.startRecording(muxer);
            if (mediaMakerConfig.saveVideoEnable) {
                isRecording = true;
            }
            return true;
        }
    }

    public boolean stopRecording() {
        synchronized (syncOp) {
            if (isWorking()) {
                videoCore.stopRecording();
                if (!isPreviewing) {
                    camera.stopPreview();
                    videoCore.updateCamTexture(null);
                    camTexture.release();
                }
            }
            isStreaming = false;
            isRecording = false;
            return true;
        }
    }

    private boolean isWorking() {
        return isStreaming || isRecording;
    }

    public boolean destroy() {
        synchronized (syncOp) {
            camera.release();
            videoCore.destroy();
            videoCore = null;
            camera = null;
            return true;
        }
    }

    public boolean swapCamera() {
        synchronized (syncOp) {
            Log.d("","StreamingClient,swapCamera()");
            camera.stopPreview();
            camera.release();
            camera = null;
            if (null == (camera = createCamera(currentCameraIndex = (++currentCameraIndex) % cameraNum))) {
                Log.e("","can not swap camera");
                return false;
            }
            mIsFrontCamera = currentCameraIndex == CameraInfo.CAMERA_FACING_FRONT;
            videoCore.setCurrentCamera(currentCameraIndex);
            CameraHelper.selectCameraFpsRange(camera.getParameters(), mediaMakerConfig);
            if (!CameraHelper.configCamera(camera, mediaMakerConfig)) {
                camera.release();
                return false;
            }
            prepareVideo();
            camTexture.release();
            videoCore.updateCamTexture(null);
            startVideo();
            videoCore.updateCamTexture(camTexture);
            return true;
        }
    }

    public boolean isFrontCamera() {
        return mIsFrontCamera;
    }

    public boolean toggleFlashLight() {
        synchronized (syncOp) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                List<String> flashModes = parameters.getSupportedFlashModes();
                String flashMode = parameters.getFlashMode();
                if (!Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
                    if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        return true;
                    }
                } else if (!Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
                    if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
    }
	public boolean toggleFlashLight(boolean on) {
        synchronized (syncOp) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                List<String> flashModes = parameters.getSupportedFlashModes();
                String flashMode = parameters.getFlashMode();
                if (on && !Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
                    if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        return true;
                    }
                } else if (!on && !Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
                    if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.w("VideoClient", "toggleFlashLight,failed" + e.getMessage());
                return false;
            }
            return false;
        }
    }

    public boolean setZoomByPercent(float targetPercent) {
        synchronized (syncOp) {
            targetPercent = Math.min(Math.max(0f, targetPercent), 1f);
            Camera.Parameters p = camera.getParameters();
            p.setZoom((int) (p.getMaxZoom() * targetPercent));
            camera.setParameters(p);
            return true;
        }
    }

    public void setHardVideoFilter(BaseHardVideoFilter baseHardVideoFilter) {
        ((VideoCore) videoCore).setVideoFilter(baseHardVideoFilter);
    }

    public void setVideoChangeListener(IVideoChange listener) {
        synchronized (syncOp) {
            if (videoCore != null) {
                videoCore.setVideoChangeListener(listener);
            }
        }
    }

    private void resoveResolution(MediaMakerConfig config, Size targetVideoSize) {
        {
            float pw, ph, vw, vh;
            if (config.isPortrait) {
                config.videoHeight = targetVideoSize.getWidth();
                config.videoWidth = targetVideoSize.getHeight();
                pw = config.previewVideoHeight;
                ph = config.previewVideoWidth;
            } else {
                config.videoWidth = targetVideoSize.getWidth();
                config.videoHeight = targetVideoSize.getHeight();
                pw = config.previewVideoWidth;
                ph = config.previewVideoHeight;
            }
            vw = config.videoWidth;
            vh = config.videoHeight;
            float pr = ph / pw, vr = vh / vw;
            if (pr == vr) {
                config.cropRatio = 0.0f;
            } else if (pr > vr) {
                config.cropRatio = (1.0f - vr / pr) / 2.0f;
            } else {
                config.cropRatio = -(1.0f - pr / vr) / 2.0f;
            }
        }
    }


}
