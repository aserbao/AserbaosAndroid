package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SlideAnimationFragment extends Fragment implements SlideItemAnimationAdapter.IItemOnTouchCallBackListener {


    @BindView(R.id.slide_animation_tv)
    TextView mSlideAnimationTv;
    @BindView(R.id.slide_animation_fl)
    CatchTouchFrameLayout mSlideAnimationFl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slide_animation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String string = getArguments().getString(StaticFinalValues.STRING);
        mSlideAnimationTv.setText(string);
        mSlideAnimationFl.setIItemOnTouchCallBackListener(this);
    }

    public SlideItemAnimationAdapter.IItemOnTouchCallBackListener mIItemOnTouchCallBackListener;

    public void setIItemOnTouchCallBackListener(SlideItemAnimationAdapter.IItemOnTouchCallBackListener itemOnTouchCallBackListener) {
        mIItemOnTouchCallBackListener = itemOnTouchCallBackListener;
    }


    @Override
    public void onScollView(View view, float scrollX, int action,int comeFrom) {
        if (mIItemOnTouchCallBackListener != null) {
            mIItemOnTouchCallBackListener.onScollView(view,scrollX,action,comeFrom);
        }
    }
}
