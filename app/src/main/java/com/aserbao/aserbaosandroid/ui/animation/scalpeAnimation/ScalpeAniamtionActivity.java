package com.aserbao.aserbaosandroid.ui.animation.scalpeAnimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.ScalpelFrameLayout;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;

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

    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();


    public void initGetData(){
        mBaseRecyclerBeen.add(new BaseRecyclerBean("1"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("2"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("3"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("4"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("5"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("6"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("7"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("8"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("9"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("10"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("11"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("12"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("13"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("14"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("15"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("16"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("17"));
    }


    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mScaleRecyclerView.setLayoutManager(linearLayoutManager);
        mScaleRecyclerView.setAdapter(mCommonAdapter);
        linearLayoutManager.scrollToPositionWithOffset(3,0);
        mScaleRecyclerView.setBackgroundResource(ImageSource.getRandomImageId());
    }

    @Override
    public void itemClickBack(View view , int position) {
        Snackbar.make(mScaleRecyclerView,"点什么点？",Snackbar.LENGTH_SHORT);
    }
}
