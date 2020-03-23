package com.example.base.commonView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.R;
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.commonData.ASourceUtil;
import com.example.base.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-16 17:05
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.commonView
 */
public class ImageViewFragment extends Fragment {
    /*@BindView(R.id.base_recycler_tv)
    public TextView mBaseRecyclerTv;
    @BindView(R.id.show_data_content_rv)
    public RecyclerView mShowDataContentRv;
    @BindView(R.id.base_recycler_view)
    public RecyclerView mOpenglRecyclerView;
    @BindView(R.id.base_recycler_view_fl)
    public RelativeLayout mBaseRecyclerViewFl;
    @BindView(R.id.base_recycler_empty_container)
    public FrameLayout mBaseRecyclerEmptyContainer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_recyclerview_activity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGetData();
        initView();
    }

    private int entrancePosition = 0;

    private void initGetData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            entrancePosition = bundle.getInt(StaticFinalValues.POSITION, 0);
        }
        mBaseRecyclerBean = ASourceUtil.getStaticRecyclerViewData(mBaseRecyclerBean, 1);
    }

    private LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;
    public List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();

    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(getContext(), getActivity(), mBaseRecyclerBean, null);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), mOrientation, false);
        mOpenglRecyclerView.setLayoutManager(mLinearLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mOpenglRecyclerView.scrollToPosition(entrancePosition);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());
    }
*/

}
