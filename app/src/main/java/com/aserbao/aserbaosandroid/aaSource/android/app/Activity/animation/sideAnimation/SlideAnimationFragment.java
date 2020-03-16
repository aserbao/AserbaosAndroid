package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.rv.adapters.SlideItemAnimationAdapter;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SlideAnimationFragment extends Fragment implements SlideItemAnimationAdapter.IItemOnTouchCallBackListener {


    @BindView(R.id.slide_animation_tv)
    TextView mSlideAnimationTv;
    @BindView(R.id.slide_animation_fl)
    CatchTouchFrameLayout mSlideAnimationFl;
    @BindView(R.id.slide_animation_rv)
    RecyclerView mSlideAnimationRv;


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
        initGetData();
        initView();
    }

    private void initGetData() {
        for (int i = 1; i < 21; i++) {
            mBaseRecyclerBean.add(new BaseRecyclerBean("第" + i + "个数据",i));
        }
    }

    public List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    private void initView() {
        String string = getArguments().getString(StaticFinalValues.STRING);
        mSlideAnimationTv.setText(string);
        mSlideAnimationFl.setIItemOnTouchCallBackListener(this);

        BaseRecyclerViewActivityAdapter mCommonAdapter = new BaseRecyclerViewActivityAdapter(getContext(), getActivity(), mBaseRecyclerBean, new IBaseRecyclerItemClickListener() {
            @Override
            public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
                Toast.makeText(getContext(), "你点击了" + position + "个位置的View 常按 =" + isLongClick, Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mSlideAnimationRv.setLayoutManager(mLinearLayoutManager);
        mSlideAnimationRv.setAdapter(mCommonAdapter);
    }

    public SlideItemAnimationAdapter.IItemOnTouchCallBackListener mIItemOnTouchCallBackListener;

    public void setIItemOnTouchCallBackListener(SlideItemAnimationAdapter.IItemOnTouchCallBackListener itemOnTouchCallBackListener) {
        mIItemOnTouchCallBackListener = itemOnTouchCallBackListener;
    }


    @Override
    public void onClickOrLongPress(boolean isLongPress, Bundle bundle) {

    }

    @Override
    public void onScollView(View view, float scrollX, int action, int comeFrom) {
        if (mIItemOnTouchCallBackListener != null) {
            mIItemOnTouchCallBackListener.onScollView(view, scrollX, action, comeFrom);
        }
    }


}
