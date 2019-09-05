package com.aserbao.aserbaosandroid.AUtils.AUI.popUtil;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;

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

    @Override
    public void onDismiss() {

    }
}
