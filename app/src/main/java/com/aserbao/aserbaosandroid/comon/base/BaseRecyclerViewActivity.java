package com.aserbao.aserbaosandroid.comon.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseSpinnerAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.canvas.canvas.CanvasPointView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/19 4:40 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base
 */
public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements IBaseRecyclerItemClickListener {
    protected static final int COME_FROM_SPINNER = 1;

    @BindView(R.id.base_recycler_tv)
    public TextView mBaseRecyclerTv;
    @BindView(R.id.show_data_content_rv)
    public RecyclerView mShowDataContentRv;
    @BindView(R.id.base_recycler_view)
    public RecyclerView mOpenglRecyclerView;
    @BindView(R.id.base_recycler_view_fl)
    public RelativeLayout mBaseRecyclerViewFl;
    @BindView(R.id.base_recycler_empty_container)
    public FrameLayout mBaseRecyclerEmptyContainer;

    @BindView(R.id.base_tool_bar)
    Toolbar mBaseToolBar;
    @BindView(R.id.base_top_tv)
    TextView mBaseTopTv;
    @BindView(R.id.base_spinner)
    Spinner mBaseSpinner;

    protected Context mContext;
    @BindView(R.id.base_up_tv)
    TextView mBaseUpTv;
    @BindView(R.id.base_down_tv)
    TextView mBaseDownTv;

    @BindView(R.id.canvas_point_view)
    protected CanvasPointView mCanvasPointView;
    protected LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();
    public List<BaseRecyclerBean> mBaseSpinnerRecyclerBeen = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslations();
        setContentView(R.layout.base_recyclerview_activity);
        ButterKnife.bind(this);
        mContext = this;
        initGetData();
        initViewForLinear();
        initViewTopSpinner();
    }

    protected  void initViewTopSpinner(){
        if (mBaseSpinnerRecyclerBeen.size() != 0) {
            mBaseToolBar.setVisibility(View.VISIBLE);
            BaseSpinnerAdapter baseSpinnerAdapter = new BaseSpinnerAdapter(this, mBaseSpinnerRecyclerBeen);
            mBaseSpinner.setAdapter(baseSpinnerAdapter);
            mBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    itemClickBack(view,position,false, COME_FROM_SPINNER);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    public void setTranslations() { }

    public int mMode = StaticFinalValues.LINEAR_LAYOUTMANAGER_VERTICAL;

    public void setMode(int mode) {
        mMode = mode;
        switch (mMode) {
            case StaticFinalValues.LINEAR_LAYOUTMANAGER_VERTICAL:
                mOrientation = LinearLayout.VERTICAL;
                break;
            case StaticFinalValues.LINEAR_LAYOUTMANAGER_HORIZONTAL:
                mOrientation = LinearLayout.HORIZONTAL;
                break;
        }
        initViewForLinear();
    }

    public abstract void initGetData();


    public void initViewForLinear() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, this);
        if (mMode == StaticFinalValues.GRID_LAYOUTMANAGER) {
            mLinearLayoutManager = new GridLayoutManager(this, 3);
        } else {
            mLinearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        }
        mOpenglRecyclerView.setLayoutManager(mLinearLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());

        mOpenglRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition <= 8) {
                    mBaseUpTv.setVisibility(View.GONE);
                    mBaseDownTv.setVisibility(View.GONE);
                } else {
                    mBaseUpTv.setVisibility(View.VISIBLE);
                    mBaseDownTv.setVisibility(View.VISIBLE);
                }
            }
        });

        mBaseUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) return;
                mOpenglRecyclerView.smoothScrollToPosition(0);
            }
        });

        mBaseDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenglRecyclerView.smoothScrollToPosition(mCommonAdapter.getItemCount() - 1);
            }
        });
    }


    public void addViewToFrameLayout(View view){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        mBaseRecyclerEmptyContainer.removeAllViews();
        mBaseRecyclerEmptyContainer.addView(view);
    }

    public View addLayoutToFrameLayout(int resLayout){
        View view = LayoutInflater.from(mContext).inflate(resLayout, null);
        addViewToFrameLayoutFullScreen(view);
        mNeedDirectBack = true;
        return view;
    }

    public void addViewToFrameLayoutFullScreen(View view){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams1 = mBaseRecyclerEmptyContainer.getLayoutParams();
        if (layoutParams1 instanceof ViewGroup.MarginLayoutParams){
            ((ViewGroup.MarginLayoutParams) layoutParams1).setMargins(0,0,0,0);
        }
        mBaseRecyclerEmptyContainer.setLayoutParams(layoutParams1);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        mBaseRecyclerEmptyContainer.removeAllViews();

        ViewGroup parent = (ViewGroup)view.getParent();
        if (parent != null) parent.removeAllViews();
        mBaseRecyclerEmptyContainer.addView(view);
    }


    boolean mNeedDirectBack = false;
    @Override
    public void onBackPressed() {
        if (mBaseRecyclerEmptyContainer.getChildCount() > 0 && !mNeedDirectBack){
            mBaseRecyclerEmptyContainer.removeAllViews();
            return;
        }
        super.onBackPressed();
    }



}
