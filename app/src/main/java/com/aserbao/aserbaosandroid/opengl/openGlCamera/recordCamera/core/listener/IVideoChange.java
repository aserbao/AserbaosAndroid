package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.core.listener;

/**
 * Created by lake on 22/09/16.
 * Librestreaming project.
 */
public interface IVideoChange {
    void onVideoSizeChanged(int width, int height);

    class VideoChangeRunable implements Runnable {
        IVideoChange videoChangeListener;
        int w, h;

        public VideoChangeRunable(IVideoChange videoChangeListener, int w, int h) {
            this.videoChangeListener = videoChangeListener;
            this.w = w;
            this.h = h;
        }

        @Override
        public void run() {
            if (videoChangeListener != null) {
                videoChangeListener.onVideoSizeChanged(w, h);
            }
        }
    }
}