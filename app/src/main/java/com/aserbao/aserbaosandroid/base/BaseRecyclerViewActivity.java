package com.aserbao.aserbaosandroid.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base
 */
public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements IBaseRecyclerItemClickListener {


    @BindView(R.id.base_recycler_tv)
    public TextView mBaseRecyclerTv;
    @BindView(R.id.show_data_content_rv)
    public RecyclerView mShowDataContentRv;
    @BindView(R.id.opengl_recycler_view)
    public RecyclerView mOpenglRecyclerView;
    @BindView(R.id.base_recycler_view_fl)
    public RelativeLayout mBaseRecyclerViewFl;
    @BindView(R.id.base_recycler_empty_container)
    public FrameLayout mBaseRecyclerEmptyContainer;

    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;

    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();
    protected Context mContext;
    @BindView(R.id.base_up_tv)
    TextView mBaseUpTv;
    @BindView(R.id.base_down_tv)
    TextView mBaseDownTv;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslations();
        setContentView(R.layout.base_activity);
        ButterKnife.bind(this);
        mContext = this;
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
        mLinearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        mOpenglRecyclerView.setLayoutManager(mLinearLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ImageSource.getRandomImageId());

        mOpenglRecyclerView.post(new Runnable() {
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
                mOpenglRecyclerView.smoothScrollToPosition(0);
            }
        });

        mBaseDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenglRecyclerView.smoothScrollToPosition(mCommonAdapter.getItemCount()-1);
            }
        });
    }
}
