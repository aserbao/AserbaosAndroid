package com.example.base.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.R;
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.adapters.BaseSpinnerAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener;
import com.example.base.utils.data.ASourceUtil;
import com.example.base.utils.data.StaticFinalValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 功能:
 * @author aserbao
 * @date : On 2019/2/19 4:40 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base
 */
public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements IBaseRecyclerItemClickListener {
    public final String TAG = getClass().getSimpleName();
    protected static final int COME_FROM_SPINNER = 1;

    public TextView mBaseRecyclerTv;
    public RecyclerView mShowDataContentRv;
    public RecyclerView mOpenglRecyclerView;
    public RelativeLayout mBaseRecyclerViewFl;
    public FrameLayout mBaseRecyclerEmptyContainer;

    Toolbar mBaseToolBar;
    TextView mBaseTopTv;
    Spinner mBaseSpinner;

    protected Context mContext;
    TextView mBaseUpTv;
    TextView mBaseDownTv;

    protected LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mRvOrientation = LinearLayoutManager.VERTICAL;
    public List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    public List<BaseRecyclerBean> mBaseSpinnerRecyclerBeen = new ArrayList<>();

    public abstract void initGetData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslations();
        setContentView(R.layout.base_recyclerview_activity);
        mContext = this;
        initView();
        initGetData();
        initViewForLinear();
        initViewTopSpinner();
        ARouter.getInstance().inject(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ARouter.getInstance().destroy();
    }

    private void initView() {
        mBaseDownTv = findViewById(R.id.base_down_tv);
        mBaseUpTv = findViewById(R.id.base_up_tv);
        mBaseSpinner = findViewById(R.id.base_spinner);
        mBaseTopTv = findViewById(R.id.base_top_tv);
        mBaseToolBar = findViewById(R.id.base_tool_bar);
        mBaseRecyclerEmptyContainer = findViewById(R.id.base_recycler_empty_container);
        mBaseRecyclerViewFl = findViewById(R.id.base_recycler_view_fl);
        mOpenglRecyclerView = findViewById(R.id.base_recycler_view);
        mShowDataContentRv = findViewById(R.id.show_data_content_rv);
        mBaseRecyclerTv = findViewById(R.id.base_recycler_tv);
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

    public int mMode = StaticFinalValues.GRID_LAYOUTMANAGER;

    public void changeOrientation(int mode,int orientation) {
        mMode = mode;
        mRvOrientation = orientation;
        initViewForLinear();
    }




    public void initViewForLinear() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBean, this);
        if (mMode == StaticFinalValues.GRID_LAYOUTMANAGER) {
            mLinearLayoutManager = new GridLayoutManager(this, 3);
            ((GridLayoutManager) mLinearLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mCommonAdapter.getSpanSize(position);
                }
            });
        } else {
            mLinearLayoutManager = new LinearLayoutManager(this, mRvOrientation, false);
        }
        mOpenglRecyclerView.setLayoutManager(mLinearLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());
        mCommonAdapter.setmOrientation(mRvOrientation);
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

    public static final int NOT_FULL_SCREEN = 0;
    public static final int FULL_SCREEN = 1;
    public static final String IS_MATCH = "ismatch";
    public static final String NEED_DIRECT_BACK = "need_direct_back";
    public static final String NEED_WH = "need_wh";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    private void addViewToFrameLayout(View view,int type,Bundle bundle){
        mNeedDirectBack = false;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        if (bundle != null){
            if (!bundle.getBoolean(IS_MATCH)) {
                layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            }
            mNeedDirectBack = bundle.getBoolean(NEED_DIRECT_BACK);
            if (bundle.getBoolean(NEED_WH)){
                layoutParams.width = bundle.getInt(WIDTH,0);
                layoutParams.height = bundle.getInt(HEIGHT,0);
            }
        }
        switch (type){
            case NOT_FULL_SCREEN: break;
            case FULL_SCREEN:
                ViewGroup.LayoutParams layoutParams1 = mBaseRecyclerEmptyContainer.getLayoutParams();
                if (layoutParams1 instanceof ViewGroup.MarginLayoutParams){
                    ((ViewGroup.MarginLayoutParams) layoutParams1).setMargins(0,0,0,0);
                }
                mBaseRecyclerEmptyContainer.setLayoutParams(layoutParams1);
                break;
        }
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        view.setLayoutParams(layoutParams);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        mBaseRecyclerEmptyContainer.removeAllViews();
        mBaseRecyclerEmptyContainer.addView(view);
    }

    /**
     * @param view
     * @param isMatch       是否是LayoutParams.MATCH_PARENT？
     * @param isFullScreen 添加的视图是否需要全屏？
     * @param isDirectBack 点击物理返回键是否直接退出当前界面？
     */
    public void addViewToFrameLayout(View view, boolean isMatch, boolean isFullScreen, boolean isDirectBack){
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_MATCH,isMatch);
        if (isFullScreen){
            bundle.putBoolean(NEED_DIRECT_BACK,isDirectBack);
            addViewToFrameLayout(view, FULL_SCREEN, bundle);
        }else {
            bundle.putBoolean(NEED_DIRECT_BACK,isDirectBack);
            addViewToFrameLayout(view, NOT_FULL_SCREEN, bundle);
        }
    }
    public View addViewWHToFL(View view,int resLayout,boolean isMatch,boolean isFullScreen,int width,int height,boolean isDirectBack){
        if (view == null) view = LayoutInflater.from(mContext).inflate(resLayout, null);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_MATCH,isMatch);
        bundle.putBoolean(NEED_WH,true);
        bundle.putInt(WIDTH,width);
        bundle.putInt(HEIGHT,height);
        if (isFullScreen){
            bundle.putBoolean(NEED_DIRECT_BACK,isDirectBack);
            addViewToFrameLayout(view, FULL_SCREEN, bundle);
        }else {
            addViewToFrameLayout(view, NOT_FULL_SCREEN, bundle);
        }
        return view;
    }


    public View addViewHToFl(View view,boolean isMatch,boolean isFullScreen,int width,int height,boolean isDirectBack){
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_MATCH,isMatch);
        bundle.putBoolean(NEED_WH,true);
        bundle.putInt(WIDTH,width);
        bundle.putInt(HEIGHT,height);
        if (isFullScreen){
            bundle.putBoolean(NEED_DIRECT_BACK,isDirectBack);
            addViewToFrameLayout(view, FULL_SCREEN, bundle);
        }else {
            addViewToFrameLayout(view, NOT_FULL_SCREEN, bundle);
        }
        return view;
    }

    public View addLayoutToFrameLayout(int resLayout, boolean needFullScreen){
        View view = LayoutInflater.from(mContext).inflate(resLayout, null);
        if(needFullScreen) {
            addViewToFrameLayout(view,true,true,true);
        }else{
            addViewToFrameLayout(view,true,false, false);
        }
        return view;
    }


    protected boolean mNeedDirectBack = false;
    @Override
    public void onBackPressed() {
        if (mBaseRecyclerEmptyContainer.getChildCount() > 0 && !mNeedDirectBack){
            mBaseRecyclerEmptyContainer.removeAllViews();
            return;
        }
        super.onBackPressed();
    }



}
