package com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.commonData.ImageSource;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/11 4:30 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest
 */
public class NestItemViewHolder extends BaseViewHolder implements IBaseRecyclerItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.nest_rv_item)
    ImageView mNestRvItem;
    @BindView(R.id.nest_rv_item_rv)
    RecyclerView mNestRvItemRv;
    @BindView(R.id.nest_rv_item_swipe_refresh_layout)
    SwipeRefreshLayout mNestRvItemSwipeRefreshLayout;

    public NestItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
    private Context mContext;

    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();

    public void setDataSource(Context context){
        mContext = context;
        mNestRvItem.setImageResource(ImageSource.getRandomImageId());
        for (int i = 0; i < 30; i++) {
            mBaseRecyclerBeen.add(new BaseRecyclerBean(String.valueOf(i)));
        }
        BaseRecyclerViewActivityAdapter mCommonAdapter = new BaseRecyclerViewActivityAdapter(context, (Activity) context, mBaseRecyclerBeen, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mNestRvItemRv.setLayoutManager(linearLayoutManager);
        mNestRvItemRv.setAdapter(mCommonAdapter);
        mNestRvItemRv.setBackgroundResource(ImageSource.getRandomImageId());
        mNestRvItemSwipeRefreshLayout.setOnRefreshListener(this);

    }

    @OnClick({R.id.nest_rv_item_tv, R.id.nest_rv_item_top_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nest_rv_item_tv:
                Toast.makeText(mContext, "搞事情?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nest_rv_item_top_tv:
                Toast.makeText(mContext, "点我？", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void itemClickBack(View view, int position) {
        Toast.makeText(mContext, String.valueOf(position)+" 被点击了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mNestRvItemSwipeRefreshLayout.setRefreshing(false);
    }
}
