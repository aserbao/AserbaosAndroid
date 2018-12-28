package com.aserbao.aserbaosandroid.functions.events.scrollEvent;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class ScrollEventActivity extends AppCompatActivity {

    private static final String TAG = "ScrollEventActivity";
    /*@BindView(R.id.texture_view)
    TextureView mTextureView;*/
    @BindView(R.id.pop_scroll_view)
    Button mPopScrollView;
    @BindView(R.id.scroll_view_iv)
    FrameLayout mScrollViewIv;
    @BindView(R.id.jz_video_player)
    MyJZVideoPlayer mJzVideoPlayer;

    private int mScreenWidth;
    private int mScreenHeight;
    private boolean onStartDown = true;//开始触摸
    private boolean isUpDownTouch = true;//是否是上下滑动
    private float mLastScale, mLastPositionY, mLastPercentAlpha, mLastRotateY;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_event);
        ButterKnife.bind(this);
        initPlayer();
        initView();
    }
    private int mCuurPosition = 0;
    public static String[] videoUrlList =
            {
                    "http://v.douyin.com/8vgcDj/",
                    "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/63f3f73712544394be981d9e4f56b612/69c5767bb9e54156b5b60a1b6edeb3b5-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/25a8d119cfa94b49a7a4117257d8ebd7/f733e65a22394abeab963908f3c336db-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/7512edd1ad834d40bb5b978402274b1a/9691c7f2d7b74b5e811965350a0e5772-5287d2089db37e62345123a1be272f8b.mp4",
                    "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
            };

    private void initPlayer() {
        mJzVideoPlayer.setUp(videoUrlList[mCuurPosition], JZVideoPlayer.CURRENT_STATE_NORMAL,"");
        mJzVideoPlayer.startVideo();
    }


    private void initView() {
        WindowManager wm = this.getWindowManager();

        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
    }

    private float mLastX;
    private float mLastY;
    private long mStartClickTime;//点击时间
    private boolean isLongClick;//是否是常按

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return true;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartClickTime = System.currentTimeMillis();
                mLastX = event.getX();
                mLastY = event.getY();
                onStartDown = true;
                mLastScale = 1.0f;
                mLastPositionY = 0.0f;
                mLastRotateY = 0.0f;
                isLongClick = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float chaX = event.getX() - mLastX;
                float chaY = event.getY() - mLastY;
                changeVideoStatus(true);
                long l = System.currentTimeMillis() - mStartClickTime;
                Log.e(TAG, "onTouchEvents: " + l  + "chaX = " + chaX + " chaY = " + chaY);
                if (onStartDown) {
                    onStartDown = false;
                    if(l > 200 && Math.abs(chaX) < 10 && Math.abs(chaY) < 10){
                        isLongClick = true;
                    }
                    if (Math.abs(chaX) < Math.abs(chaY)) {
                        isUpDownTouch = true;
                    } else {
                        isUpDownTouch = false;
                    }
                }
                if(!isLongClick &&  Math.abs(chaX) > 10 && Math.abs(chaY) > 10) {
                    if (isUpDownTouch) {
                        dealUpAndDown(chaY);
                        Log.e(TAG, "onTouchEvent: " + "上下滑动 滑动的Y值为：" + chaY);
                    } else {
                        dealLeftAndRight(chaX);
                        Log.e(TAG, "onTouchEvent: " + "左右滑动 滑动的X值为：" + chaX);
                    }
                }
                Log.e(TAG, "onTouchEvent: ChaX = " + chaX + "   chaY = " + chaY + "屏幕高度：height = " + mScreenHeight + "width = " + mScreenWidth);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float chafX = event.getX() - mLastX;
                float chafY = event.getY() - mLastY;
                if(System.currentTimeMillis() - mStartClickTime < 500 && Math.abs(chafX) < 10 && Math.abs(chafY) < 10) {
                    if(mLastX < mScreenWidth / 2){
                        beforeClikc();
                        Log.e(TAG, "onTouchEvent: 左半边点击"  );
                    }else{
                        nextClick();
                        Log.e(TAG, "onTouchEvent: 右半边点击"  );
                    }
                }else {
                    returnNormal();
                }

                changeVideoStatus(false);
                Log.e(TAG, "onTouchEvent最终滑动距离: ChaX = " + chafX + "   chaY = " + chafY);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void beforeClikc() {
        mCuurPosition--;
        mJzVideoPlayer.setUp(videoUrlList[mCuurPosition], JZVideoPlayer.CURRENT_STATE_NORMAL,"");
        mJzVideoPlayer.startVideo();
    }

    private void nextClick() {
        mCuurPosition++;
        mJzVideoPlayer.setUp(videoUrlList[mCuurPosition], JZVideoPlayer.CURRENT_STATE_NORMAL,"");
        mJzVideoPlayer.startVideo();
    }
    public void changeVideoStatus(boolean isPause){
        /*if (JZMediaManager.instance().media != null) {
            mCuurVideoIsPause = isPause;
            if(isPause){
                mMediaPlayer.pause();
            }else{
                mMediaPlayer.start();
            }
        }*/

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
        mLastRotateY = 360 * v / 30;
        mLastPercentAlpha = 1.0f;
        ObjectAnimator.ofFloat(mScrollViewIv, "rotationY", 0, mLastRotateY).setDuration(0).start();
        Log.e(TAG, "dealLeftAndRight: " + v + " mLastScale ：" + mLastScale + " V1 = " + mLastRotateY);
    }

    public void returnNormal() {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", mLastScale, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", mLastScale, 1.0f);
        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY", mLastRotateY, 0.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", Math.abs(mLastPercentAlpha), 1.0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("Y", mLastPositionY, 0.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mScrollViewIv, scaleX, scaleY, alpha, translationY, rotationY);
        Log.e(TAG, "returnNormal: " + "scaleX" + mLastScale + "rotationY =" + mLastRotateY + " alpha = " + mLastPercentAlpha + " Y = " + mLastPositionY);
        animator.setDuration(500);
        animator.start();
    }


}
