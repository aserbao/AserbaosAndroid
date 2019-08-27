package com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.adapters.RecyclerEventAdapter;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.layoutManagers.ARecyclerEventLinearManager;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.recyclers.AEventRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 可以直接参考MoveDragActivity的使用
 */
public class RecyclerViewEventActivity extends AppCompatActivity {
    private static final String TAG = "Activiy  **** ====  **** ====  **** ====  ";
    @BindView(R.id.recycler_event_rv)
    AEventRecyclerView mRecyclerEventRv;
    @BindView(R.id.recycler_event_rl)
    RelativeLayout mTopVideoParentRl;
    private ARecyclerEventLinearManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_event);
        ButterKnife.bind(this);
        RecyclerEventAdapter recyclerEventAdapter = new RecyclerEventAdapter(this);
        mLinearLayoutManager = new ARecyclerEventLinearManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerEventRv.setLayoutManager(mLinearLayoutManager);
        mRecyclerEventRv.setAdapter(recyclerEventAdapter);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerEventRv);
    }
    private int mScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int mScreenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private long mStartClickDownTime, mLastClickDownTime, mLastStartClickTime;//点击时间
    private float mLastX;
    private float mLastY;
    private boolean isLongClick,isDrag,mFirstDrag,mIsUpAndDown;//是否是常按,是否拖动

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartClickDownTime = System.currentTimeMillis();
                mLastX = ev.getX();
                mLastY = ev.getY();
                mFirstDrag = true;
                Log.e(TAG, "dispatchTouchEvent: ACTION_DOWN ===== "   + b);
                break;
            case MotionEvent.ACTION_MOVE:
                float chaX = ev.getX() - mLastX;
                float chaY = ev.getY() - mLastY;
                long l = System.currentTimeMillis() - mStartClickDownTime;
                if(l > 200 && Math.abs(chaX) + Math.abs(chaY) > 200){
                    isDrag = true;
                }
                if(isDrag & mFirstDrag){
                    mFirstDrag = false;
                    if(Math.abs(chaX) > Math.abs(chaY)){
                        mIsUpAndDown = false;
                        Log.e(TAG, "dispatchTouchEvent:  左右滑动 ");
                    }else{
                        mIsUpAndDown = true;
                        Log.e(TAG, "dispatchTouchEvent:  上下滑动 ");
                    }
                }

                if(mIsUpAndDown){
                    b = false;
                    dealUpAndDown(chaY, chaX);
                    mLinearLayoutManager.setCanScrollHorizontally(false);
                }else{
                    mLinearLayoutManager.setCanScrollHorizontally(true);
                    return super.dispatchTouchEvent(ev);
                }

                Log.e(TAG, "dispatchTouchEvent: ACTION_MOVE ===== "   + b + "  chaX = " + chaX +  "  chaY = " + chaY);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent: ACTION_UP ===== "   + b);
                isLongClick = false;
                float chafX = ev.getX() - mLastX;
                float chafY = ev.getY() - mLastY;
                if (System.currentTimeMillis() - mStartClickDownTime < 400 && !isDrag) {//判断是否是点击事件
                    if (System.currentTimeMillis() - mLastClickDownTime > 100) {//防止快速点击
                        if( Math.abs(chafX) < 20 &&Math.abs(chafY) < 20){
                            Toast.makeText(this, "点击事件", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else if(isDrag){
                    if(chafY > 500){
                        onBackPressed();
                    }else {
                        returnNormal();
                    }
                }
                mLastClickDownTime = System.currentTimeMillis();
                break;
        }
        return b;
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean b = super.onTouchEvent(e);
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: ACTION_DOWN ===== "   + b);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: ACTION_MOVE ===== "   + b );
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP ===== "   + b);
                break;
        }
        return b;
    }
    private float mLastScale, mLastPositionY,mLastPositionX, mLastPercentAlpha, mLastRotateY;

    public void dealUpAndDown(float chaY,float chaX) {
        if (mTopVideoParentRl == null) {
            return;
        }
        float v = -chaY / mScreenHeight;
//        mLastPercentAlpha = 1 + v;
        mLastPercentAlpha = 1;
        mLastScale = 1 + v / 5;
        mTopVideoParentRl.setScaleX(mLastScale);
        mTopVideoParentRl.setScaleY(mLastScale);
        mLastPositionY = chaY;
        ObjectAnimator.ofFloat(mTopVideoParentRl, "Y", 0, mLastPositionY).setDuration(0).start();


        float vX = -chaX / mScreenWidth;
        mLastPositionX = chaX;
        ObjectAnimator.ofFloat(mTopVideoParentRl, "X", 0, mLastPositionX).setDuration(0).start();
    }

    public void returnNormal() {
        if (mTopVideoParentRl == null) {
            return;
        }
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", mLastScale, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", mLastScale, 1.0f);
        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY", mLastRotateY, 0.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", Math.abs(mLastPercentAlpha), 1.0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("Y", mLastPositionY, 0.0f);
        PropertyValuesHolder mBottomPaint = PropertyValuesHolder.ofFloat("X", mLastPositionX, 0.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTopVideoParentRl, scaleX, scaleY, translationY, rotationY,mBottomPaint);
        animator.setDuration(300);
        animator.start();
    }
}
