package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.scale_smooth_slide_one_method.library;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/25
 * email: this is empty email
 */
public class CardAdapterHelper {
    private static final String TAG = "CardAdapterHelper";
    private int mPagePadding = 20;
    private int mShowLeftCardWidth = 30;

    public void onCreateViewHolder(ViewGroup parent, View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        int width = parent.getWidth() - DisplayUtil.dip2px(2 * (mPagePadding + mShowLeftCardWidth));
        lp.width = width;
        Log.e(TAG, "onCreateViewHolder: " + width);
        itemView.setLayoutParams(lp);
    }

    public void onBindViewHolder(View itemView, final int position, int itemCount) {
        int padding = DisplayUtil.dip2px( mPagePadding);
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = position == 0 ? padding + DisplayUtil.dip2px( mShowLeftCardWidth) : 0;
        int rightMarin = position == itemCount - 1 ? padding + DisplayUtil.dip2px( mShowLeftCardWidth) : 0;
        Log.e(TAG, "onBindViewHolder: " + leftMarin + " rightMargin = "+ rightMarin );
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);
    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }

    public void setPagePadding(int pagePadding) {
        mPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        mShowLeftCardWidth = showLeftCardWidth;
    }
}
