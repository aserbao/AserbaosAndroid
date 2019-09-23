package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.date.ADateMgr;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

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
    public void onScollView(View view, float scrollX,int  action,int comeFrom) {
        switch (comeFrom){
            case CatchTouchFrameLayout.CATCH_TOUCH_FRAME_LAYOUT:
                int screenWidth = AserbaoApplication.screenWidth;
                if (mAnimationItemView.getX() < - screenWidth && scrollX < 0){
                    Log.e(TAG, "onScollView: 条件成立了" );
                    return;
                }
                scrollX = scrollX - screenWidth;
                break;
            case CatchTouchCardView.CATCH_TOUCH_CARF_VIEW:
                if (MotionEvent.ACTION_DOWN == action) {
                    replaceFragment();
                    mAnimationItemView = view;
                    return;
                }
                if (mAnimationItemView.getX() > 0 && scrollX > 0) return;
                break;
        }
        ObjectAnimator.ofFloat(mSideAnimationFl,"translationX",0,scrollX).setDuration(0).start();
        ObjectAnimator.ofFloat(mAnimationItemView,"translationX",0,scrollX).setDuration(0).start();
        Log.d(TAG, "onScollView() called with: view = [" + view + "], scrollX = [" + scrollX + "], action = [" + action + "], comeFrom = [" + comeFrom + "]\n"
            + mSideAnimationFl.getX() + " mAnimationItemView .x = " +  mAnimationItemView.getX());
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
