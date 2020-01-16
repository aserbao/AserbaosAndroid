package com.aserbao.aserbaosandroid.AUtils.AUI.popUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.demo.PopBean;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.popupwindow.demo.adapters.PopSimpleDemoAdapter;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * 微信公众号「aserbaocool」
 * @date : On 2019-09-05 11:26
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.AUI.popUtil
 */
public class PopupManager implements PopupWindow.OnDismissListener{

    private Context mContext;
    private PopupWindow mCuurPoupWindow;

    public PopupManager(Context mContext) {
        this.mContext = mContext;
    }

    public void showAnimationConfigure(){
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.a_pop_tween_animation_layout, null);
        mCuurPoupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mCuurPoupWindow.setOutsideTouchable(false);
        mCuurPoupWindow.setFocusable(true);
        mCuurPoupWindow.setOnDismissListener(this);
        mCuurPoupWindow.setBackgroundDrawable(new BitmapDrawable());
        mCuurPoupWindow.setAnimationStyle(R.style.pop_bottom_to_center_anim_style);
        mCuurPoupWindow.showAtLocation(rootView.getRootView(), Gravity.BOTTOM, 0, 0);
    }

    public void showSelectedPop(Activity activity, List<BaseRecyclerBean> list){
        showSelectedPop(activity,list,null);
    }

    public void showSelectedPop(Activity activity, List<BaseRecyclerBean> list, IBaseRecyclerItemClickListener listener){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_simple_demo, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pop_simple_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        BaseRecyclerViewActivityAdapter baseRecyclerViewActivityAdapter = new BaseRecyclerViewActivityAdapter(mContext,activity, list, listener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(baseRecyclerViewActivityAdapter);

        mCuurPoupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mCuurPoupWindow.setOutsideTouchable(true);
        mCuurPoupWindow.setFocusable(true);
        mCuurPoupWindow.setOnDismissListener(this);
        mCuurPoupWindow.setBackgroundDrawable(new BitmapDrawable());
        mCuurPoupWindow.setAnimationStyle(R.style.pop_bottom_to_center_anim_style);
        mCuurPoupWindow.showAtLocation(view.getRootView(), Gravity.BOTTOM, 0, 0);
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
