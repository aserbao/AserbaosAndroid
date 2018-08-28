package com.aserbao.aserbaosandroid.functions.events.scrollEvent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollEventActivity extends AppCompatActivity {

    private static final String TAG = "ScrollEventActivity";
    @BindView(R.id.texture_view)
    TextureView mTextureView;
    @BindView(R.id.pop_scroll_view)
    Button mPopScrollView;
    @BindView(R.id.scroll_view_iv)
    ImageView mScrollViewIv;
    private int mScreenWidth;
    private int mScreenHeight;
    private boolean onStartDown = true;//开始触摸
    private boolean isUpDownTouch = true;//是否是上下滑动
    private int mImageHeight;
    private int mImageWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_event);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        WindowManager wm = this.getWindowManager();

        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
        mScrollViewIv.post(new Runnable() {
            @Override
            public void run() {
                mImageHeight = mScrollViewIv.getHeight();
                mImageWidth = mScrollViewIv.getWidth();
            }
        });

    }

    private float mLastX;
    private float mLastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                onStartDown = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float chaX = event.getX() - mLastX;
                float chaY = event.getY() - mLastY;
                if(onStartDown) {
                    onStartDown = false;
                    if (Math.abs(chaX) < Math.abs(chaY)) {
                        isUpDownTouch = true;
                    } else {
                        isUpDownTouch = false;
                    }
                }
                if(isUpDownTouch){
                    dealUpAndDown(chaY);
                    Log.e(TAG, "onTouchEvent: " + "上下滑动");
                }else{
                    Log.e(TAG, "onTouchEvent: " + "左右滑动");
                }
                Log.e(TAG, "onTouchEvent: ChaX = " + chaX + "   chaY = " + chaY + "屏幕高度：height = " + mScreenHeight + "width = " + mScreenWidth);
                break;
            case MotionEvent.ACTION_UP:
                float chafX = event.getX() - mLastX;
                float chafY = event.getY() - mLastY;
                Log.e(TAG, "onTouchEvent最终滑动距离: ChaX = " + chafX + "   chaY = " + chafY);
                break;
        }
        return super.onTouchEvent(event);
    }
    public void dealUpAndDown(float chaY){
        float v = -chaY / mScreenHeight;
        float scaleX = 1 + v;
        Log.e(TAG, "dealUpAndDown: " + scaleX);
        mScrollViewIv.setScaleX(scaleX);
        mScrollViewIv.setScaleY(scaleX);
        float absY = Math.abs(chaY);
//        mScrollViewIv.scrollTo(0,-(int)chaY);
        mScrollViewIv.layout(0,(int)absY,720,(int)absY + mScrollViewIv.getHeight());
    }

    public void returnNormal( ){

    }

    public void dealLeftAndRight(float chaX){

    }
}
