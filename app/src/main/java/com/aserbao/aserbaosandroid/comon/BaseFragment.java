package com.aserbao.aserbaosandroid.comon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-19 10:57
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon
 */
public abstract class BaseFragment extends Fragment implements IBaseRecyclerItemClickListener {

    @BindView(R.id.base_recycler_tv)
    public TextView mBaseRecyclerTv;
    @BindView(R.id.show_data_content_rv)
    public RecyclerView mShowDataContentRv;
    @BindView(R.id.base_recycler_view)
    public RecyclerView mBaseRecyclerView;
    @BindView(R.id.base_recycler_view_fl)
    public RelativeLayout mBaseRecyclerViewFl;
    @BindView(R.id.base_recycler_empty_container)
    public FrameLayout mBaseRecyclerEmptyContainer;

    protected Context mContext;
    @BindView(R.id.base_up_tv)
    TextView mBaseUpTv;
    @BindView(R.id.base_down_tv)
    TextView mBaseDownTv;
    public LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();


    public abstract void initGetData();

    public void setLinearLayoutOrientationHorizontal() {
        mOrientation = LinearLayoutManager.HORIZONTAL;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_recyclerview_activity, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGetData();
        initView();
    }

    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(getContext(), getActivity(), mBaseRecyclerBeen, this);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), mOrientation, false);
        mBaseRecyclerView.setLayoutManager(mLinearLayoutManager);
        mBaseRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());

        mBaseRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition <= 8){
                    mBaseUpTv.setVisibility(View.GONE);
                    mBaseDownTv.setVisibility(View.GONE);
                }else{
                    mBaseUpTv.setVisibility(View.VISIBLE);
                    mBaseDownTv.setVisibility(View.VISIBLE);
                }
            }
        });

        mBaseUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) return;
                mBaseRecyclerView.smoothScrollToPosition(0);
            }
        });

        mBaseDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseRecyclerView.smoothScrollToPosition(mCommonAdapter.getItemCount()-1);
            }
        });
    }
}
