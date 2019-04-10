package com.aserbao.aserbaosandroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.commonData.ImageSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/19 4:40 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base
 */
public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements IBaseRecyclerItemClickListener {
    @BindView(R.id.base_recycler_tv)
    public TextView mBaseRecyclerTv;
    @BindView(R.id.opengl_recycler_view)
    public RecyclerView mOpenglRecyclerView;
    @BindView(R.id.base_recycler_view_fl)
    public RelativeLayout mBaseRecyclerViewFl;
    @BindView(R.id.base_recycler_empty_container)
    public FrameLayout mBaseRecyclerEmptyContainer;

    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;

    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslations();
        setContentView(R.layout.base_activity);
        ButterKnife.bind(this);
        initGetData();
        initView();
    }

    public void setTranslations() {
    }

    public abstract void initGetData();


    public void setLinearLayoutOrientationHorizontal() {
        mOrientation = LinearLayoutManager.HORIZONTAL;
    }

    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        mOpenglRecyclerView.setLayoutManager(linearLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ImageSource.getRandomImageId());
    }
}
