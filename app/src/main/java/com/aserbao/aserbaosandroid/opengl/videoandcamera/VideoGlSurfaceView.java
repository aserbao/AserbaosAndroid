package com.aserbao.aserbaosandroid.opengl.videoandcamera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.Surface;

import com.aserbao.aserbaosandroid.opengl.videoshow.VideoGlRender;
import com.example.base.utils.log.ALogUtils;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author: aserbao
 * @date:2020/8/15 11:26 AM
 * @package:com.aserbao.aserbaosandroid.opengl.videoshow
 * @describle: 本地视频使用GlSurfaceView显示
 */
public class VideoGlSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener{
    private static final String TAG = "VideoPreviewView";
    private MediaPlayer mMediaPlayer;
    private VideoandCameraGlRender videoGlRender;

    public VideoGlSurfaceView(Context context) {
        super(context,null);
    }

    public VideoGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        setPreserveEGLContextOnPause(false);
        setCameraDistance(100);
        //初始化Drawer和VideoPlayer
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        videoGlRender = new VideoandCameraGlRender(context);
        setVideoPath("");
    }
    /**设置视频的播放地址*/
    public void setVideoPath(String paths){
        try {
            paths= "https://js2.a.yximgs.com/upic/2018/09/19/11/BMjAxODA5MTkxMTUyNDFfMTkwNDU3NzIxXzgwOTM4NzUxOTNfMV8z_b_B665d02cce501acfdd90fc3614e24aa20.mp4";
            mMediaPlayer.setDataSource(paths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        videoGlRender.onSurfaceCreated(gl,config);
        SurfaceTexture surfaceTexture = videoGlRender.getSurfaceTexture();
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                requestRender();
            }
        });
        Surface surface = new Surface(surfaceTexture);
        mMediaPlayer.setSurface(surface);
        try {
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.setVolume(0,0);
                    mMediaPlayer.start();
                }
            });
        } catch (Exception e) {
            ALogUtils.d(TAG, "onSurfaceCreated fail =" + e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        videoGlRender.onSurfaceChanged(gl,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        videoGlRender.onDrawFrame(gl);
    }

    /**
     * isPlaying now
     * */
    public boolean isPlaying(){
        return mMediaPlayer.isPlaying();
    }
    /**
     * pause play
     * */
    public void pause(){
        mMediaPlayer.pause();
    }
    /**
     * start play video
     * */
    public void start(){
        mMediaPlayer.start();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
