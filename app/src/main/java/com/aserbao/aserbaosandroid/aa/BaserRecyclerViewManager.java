package com.aserbao.aserbaosandroid.aa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-27 20:12
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aa
 */
public class BaserRecyclerViewManager implements PopupWindow.OnDismissListener{

    private Context mContext;
    private Activity mActivity;
    PopupWindow mCuurPoupWindow;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();

    public BaserRecyclerViewManager(Context mContext, Activity activity) {
        this.mContext = mContext;
        mActivity = activity;
    }

    public void addBaserRecyclerBean(BaseRecyclerBean baseRecyclerBean){
        mBaseRecyclerBeen.add(baseRecyclerBean);
    }



    public void showBottomRecyclerViewPop(IBaseRecyclerItemClickListener iBaseRecyclerItemClickListener){
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.base_recyclerview_activity, null);
        mCuurPoupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mCuurPoupWindow.setAnimationStyle(R.style.pop_bottom_to_center_anim_style);
        mCuurPoupWindow.setOutsideTouchable(false);
        mCuurPoupWindow.setFocusable(true);
        mCuurPoupWindow.setOnDismissListener(this);
        mCuurPoupWindow.setBackgroundDrawable(new BitmapDrawable());
        mCuurPoupWindow.showAtLocation(rootView.getRootView(), Gravity.BOTTOM, 0, 0);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.base_recycler_view);
        rootView.findViewById(R.id.base_recycler_view_fl).setBackgroundColor(Color.WHITE);
        BaseRecyclerViewActivityAdapter mCommonAdapter = new BaseRecyclerViewActivityAdapter(mContext, mActivity, mBaseRecyclerBeen, iBaseRecyclerItemClickListener);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(mCommonAdapter);
    }

    public void dismiss(){
        if (mCuurPoupWindow != null) {
            mCuurPoupWindow.dismiss();
        }
    }
    @Override
    public void onDismiss() {

    }
}
