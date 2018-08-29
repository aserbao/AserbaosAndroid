package com.aserbao.aserbaosandroid.functions.events.scrollEvent;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollEventActivity extends AppCompatActivity {

    private static final String TAG = "ScrollEventActivity";
    /*@BindView(R.id.texture_view)
    TextureView mTextureView;*/
    @BindView(R.id.pop_scroll_view)
    Button mPopScrollView;
    @BindView(R.id.scroll_view_iv)
    FrameLayout mScrollViewIv;
    @BindView(R.id.texture_view)
//    TextureView mScrollViewIv;
    TextureView mTextureView;

    private int mScreenWidth;
    private int mScreenHeight;
    private boolean onStartDown = true;//开始触摸
    private boolean isUpDownTouch = true;//是否是上下滑动
    private float mLastScale, mLastPositionY,mLastPercentAlpha,mLastRotateY;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_event);
        ButterKnife.bind(this);
        initPlayer();
        initView();
    }

    private void initPlayer() {
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                // SurfaceTexture准备就绪
                openMediaPlayer(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                // SurfaceTexture缓冲大小变化
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                // SurfaceTexture即将被销毁
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                // SurfaceTexture通过updateImage更新
            }
        });


    }

    private void openMediaPlayer(SurfaceTexture surface) {
        mediaPlayer = new MediaPlayer();
        Uri parse = Uri.parse("http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4");
        try {
            mediaPlayer.setDataSource(this,parse);
            mediaPlayer.setSurface(new Surface(surface));
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        WindowManager wm = this.getWindowManager();

        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
    }

    private float mLastX;
    private float mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                onStartDown = true;
                mLastScale = 1.0f;
                mLastPositionY = 0.0f;
                mLastRotateY = 0.0f;
                break;
            case MotionEvent.ACTION_MOVE:
                float chaX = event.getX() - mLastX;
                float chaY = event.getY() - mLastY;
                if (onStartDown) {
                    onStartDown = false;
                    if (Math.abs(chaX) < Math.abs(chaY)) {
                        isUpDownTouch = true;
                    } else {
                        isUpDownTouch = false;
                    }
                }
                if (isUpDownTouch) {
                    dealUpAndDown(chaY);
                    Log.e(TAG, "onTouchEvent: " + "上下滑动 滑动的Y值为：" + chaY);
                } else {
                    dealLeftAndRight(chaX);
                    Log.e(TAG, "onTouchEvent: " + "左右滑动 滑动的X值为：" + chaX);
                }
                Log.e(TAG, "onTouchEvent: ChaX = " + chaX + "   chaY = " + chaY + "屏幕高度：height = " + mScreenHeight + "width = " + mScreenWidth);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float chafX = event.getX() - mLastX;
                float chafY = event.getY() - mLastY;
                returnNormal();
                Log.e(TAG, "onTouchEvent最终滑动距离: ChaX = " + chafX + "   chaY = " + chafY);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void dealUpAndDown(float chaY) {
        float v = -chaY / mScreenHeight;
        mLastPercentAlpha = 1 + v;
        mLastScale = 1 + v / 5;
        mScrollViewIv.setScaleX(mLastScale);
        mScrollViewIv.setScaleY(mLastScale);
        mScrollViewIv.setAlpha(Math.abs(mLastPercentAlpha));
        mLastPositionY = chaY;
        ObjectAnimator.ofFloat(mScrollViewIv, "Y", 0, mLastPositionY).setDuration(0).start();
    }

    public void dealLeftAndRight(float chaX) {
        float v = chaX / mScreenWidth;
        mLastRotateY = 360 * v/30;
        mLastPercentAlpha = 1.0f;
        ObjectAnimator.ofFloat(mScrollViewIv, "rotationY", 0, mLastRotateY).setDuration(0).start();
        Log.e(TAG, "dealLeftAndRight: " + v + " mLastScale ：" + mLastScale  + " V1 = " + mLastRotateY);
    }
    public void returnNormal() {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", mLastScale, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", mLastScale, 1.0f);
        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY", mLastRotateY, 0.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", Math.abs(mLastPercentAlpha), 1.0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("Y", mLastPositionY, 0.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mScrollViewIv, scaleX, scaleY, alpha, translationY,rotationY);
        Log.e(TAG, "returnNormal: " + "scaleX" + mLastScale + "rotationY ="  + mLastRotateY  + " alpha = " + mLastPercentAlpha + " Y = " + mLastPositionY  );
        animator.setDuration(500);
        animator.start();
    }


}
