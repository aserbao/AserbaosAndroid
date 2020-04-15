package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.example.base.utils.date.ADateMgr;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.data.StaticFinalValues;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideItemAnimationActivity extends AppCompatActivity implements SlideItemAnimationAdapter.IItemOnTouchCallBackListener {

    @BindView(R.id.side_animation_rv)
    RecyclerView mSideAnimationRv;
    @BindView(R.id.side_animation_fl)
    FrameLayout mSideAnimationFl;
    private SlideItemAnimationAdapter mCommonAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    protected List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    private SlideAnimationFragment slideAnimationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_item_animation);
        ButterKnife.bind(this);
        initGetData();
        initView();
    }

    private void initGetData() {
        for (int i = 0; i < 20; i++) {
            mBaseRecyclerBean.add(new BaseRecyclerBean(String.valueOf(i),i));
        }
    }

    private void initView() {
        initViewForLinear();
    }

    public void initViewForLinear() {
        mCommonAdapter = new SlideItemAnimationAdapter(this, this, mBaseRecyclerBean, this);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSideAnimationRv.setLayoutManager(mLinearLayoutManager);
        mSideAnimationRv.setAdapter(mCommonAdapter);
    }
    private View mAnimationItemView;

    private static final String TAG = "SlideItemAnimationActiv";

    @Override
    public void onClickOrLongPress(boolean isLongPress, Bundle bundle) {

    }
    public static final int duration = 200;
    private final int screenWidth = AserbaoApplication.screenWidth;
    private final double limitWidth = AserbaoApplication.screenWidth/3;

    @Override
    public void onScollView(View view, float scrollX,int  action,int comeFrom) {
        switch (comeFrom){
            case CatchTouchCardView.CATCH_TOUCH_CARF_VIEW:
                rvItemScroller(view, scrollX, action);
                break;
            case CatchTouchFrameLayout.CATCH_TOUCH_FRAME_LAYOUT:
                if (scrollX < 0){
                    return;
                }
                switch (action){
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (scrollX > limitWidth) {//关闭
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", scrollX - screenWidth, 0);
                            translationX.setInterpolator(new AccelerateDecelerateInterpolator());
                            translationX.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                }
                            });
                            translationX.setDuration(duration).start();
                        }else{
                            ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", scrollX- screenWidth,-screenWidth).setDuration(duration).start();
                        }
                        break;
                    default:
                        scrollX = scrollX - screenWidth;
                        if (mAnimationItemView != null && mSideAnimationFl != null) {
                            ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", 0, scrollX).setDuration(0).start();
//                            ObjectAnimator.ofFloat(mAnimationItemView, "translationX", 0, scrollX).setDuration(0).start();
                        }
                }
                break;
        }
        Log.e(TAG, "onScollView: action = " + action + " scrollX = " + scrollX );
    }

    private void rvItemScroller(View view, float scrollX, int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                slideAnimationFragment = null;
                mAnimationItemView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                if (slideAnimationFragment == null) {
                    replaceFragment();
                }
                if (mAnimationItemView != null && slideAnimationFragment != null) {
                    ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", 0, scrollX).setDuration(0).start();
//                            ObjectAnimator.ofFloat(mAnimationItemView, "translationX", 0, scrollX).setDuration(0).start();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (Math.abs(scrollX) > limitWidth) { //打开
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", scrollX, -screenWidth);
                    translationX.setInterpolator(new AccelerateDecelerateInterpolator());
                    translationX.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    translationX.setDuration(duration).start();
                } else {
                    ObjectAnimator.ofFloat(mSideAnimationFl, "translationX", scrollX, 0).setDuration(duration).start();
                }
                break;

        }
    }



    public void replaceFragment(){
        slideAnimationFragment = new SlideAnimationFragment();
        Bundle bundle = new Bundle();
        String value = ADateMgr.date2String(new Date());
        bundle.putString(StaticFinalValues.STRING, value);
        slideAnimationFragment.setArguments(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.side_animation_fl, slideAnimationFragment);
        fragmentTransaction.commit();

        slideAnimationFragment.setIItemOnTouchCallBackListener(this);
    }


}
