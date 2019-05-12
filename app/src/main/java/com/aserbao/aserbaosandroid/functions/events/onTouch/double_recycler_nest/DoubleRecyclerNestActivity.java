package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoubleRecyclerNestActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.double_recycler_view)
    RecyclerView mDoubleRecyclerView;
    @BindView(R.id.double_recyler_srl)
    SwipeRefreshLayout mDoubleRecylerSrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_nest);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        DoubleAdapter doubleAdapter = new DoubleAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mDoubleRecyclerView.setLayoutManager(linearLayoutManager);
        mDoubleRecyclerView.setAdapter(doubleAdapter);
        new PagerSnapHelper().attachToRecyclerView(mDoubleRecyclerView);
        mDoubleRecylerSrl.setOnRefreshListener(this);
    }

    @OnClick(R.id.double_recycler_view_btn)
    public void onViewClicked() {
        Toast.makeText(this, "我在顶部你就点我！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mDoubleRecylerSrl.setRefreshing(false);
    }
}
