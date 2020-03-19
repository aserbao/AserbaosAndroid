package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoubleRecyclerNestActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,IItemOnLongClickListener{

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

    private static final String TAG = "DoubleRecyclerNestActiv";
    private void initView() {
        DoubleAdapter doubleAdapter = new DoubleAdapter(this,this);
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

    @Override
    public void onLongclick(View view) {
        createPopupWindow();
    }

    private void createPopupWindow() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.popup_window_half_screen, null);
        PopupWindow popupWindow = new PopupWindow(rootView.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rootView.getRootView(), Gravity.BOTTOM,0,0);
    }
}
