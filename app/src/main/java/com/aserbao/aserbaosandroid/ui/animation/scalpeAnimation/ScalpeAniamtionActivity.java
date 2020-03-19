package com.aserbao.aserbaosandroid.ui.animation.scalpeAnimation;

import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.ScalpelFrameLayout;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScalpeAniamtionActivity extends AppCompatActivity implements IBaseRecyclerItemClickListener {

    @BindView(R.id.scale_recycler_view)
    RecyclerView mScaleRecyclerView;
    @BindView(R.id.scalpel_one_fl)
    ScalpelFrameLayout mScalpelFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_base_one_layout);
        ButterKnife.bind(this);
        initGetData();
        initView();
        mScalpelFl.setLayerInteractionEnabled(true);
        findViewById(android.R.id.content).setBackgroundColor(Color.TRANSPARENT);
    }



    public BaseRecyclerViewActivityAdapter mCommonAdapter;

    public List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();


    public void initGetData(){
        mBaseRecyclerBean.add(new BaseRecyclerBean("1"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("2"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("3"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("4"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("5"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("6"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("7"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("8"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("9"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("10"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("11"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("12"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("13"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("14"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("15"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("16"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("17"));
    }


    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBean, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mScaleRecyclerView.setLayoutManager(linearLayoutManager);
        mScaleRecyclerView.setAdapter(mCommonAdapter);
        linearLayoutManager.scrollToPositionWithOffset(3,0);
        mScaleRecyclerView.setBackgroundResource(ASourceUtil.getRandomImageId());
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        Snackbar.make(mScaleRecyclerView,"点什么点？",Snackbar.LENGTH_SHORT);
    }
}
