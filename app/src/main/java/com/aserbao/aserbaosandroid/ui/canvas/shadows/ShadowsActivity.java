package com.aserbao.aserbaosandroid.ui.canvas.shadows;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.shadows.views.ShadowTextView;

public class ShadowsActivity extends BaseRecyclerViewActivity {


    ShadowTextView mShadowTv;
    LinearLayout mShadowll;
    private int  width = DisplayUtil.dip2px(480),lastWidth = width;
    private int height = DisplayUtil.dip2px(720),lastHeight = height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        // TODO: 2019-08-06 写一个阴影库供大家使用
        mBaseRecyclerBeen.add(new BaseRecyclerBean("缩小", 3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("增大", 4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带阴影的TextView", 1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带阴影的Fragment", 2));
        mBaseRecyclerEmptyContainer.setVisibility(View.GONE);
        for (int i = 0; i < 100; i++) {
//            result = result+ " 第几条数据 + " + String.valueOf(i) + " \n";
            result = result+ "/::>" + String.valueOf(i) + " \n";
        }
    }
    String result ;

    @Override
    public void itemClickBack(View view, int position) {
        switch (position) {
            case 1:
            case 2:
                if (mBaseRecyclerEmptyContainer.getVisibility() == View.VISIBLE) {
                    mBaseRecyclerEmptyContainer.setVisibility(View.GONE);
                    return;
                }
                mBaseRecyclerEmptyContainer.removeAllViews();
                mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
                mBaseRecyclerEmptyContainer.setBackgroundColor(Color.WHITE);
                addShadowTextView();
                break;
            case 3:
                mShadowll.removeAllViews();
                width = (int)(width  *  (1 - 0.05f));
                height= (int)(height *  (1 - 0.05f));

                float v = lastHeight / (float) height;
                if (mShadowTv != null) {
                    scale = v;
                    mShadowTv.matrix(scale);
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
                mShadowTv.setLayoutParams(layoutParams);
                mShadowll.addView(mShadowTv);
                break;
            case 4:
                width = (int)(width  *  (1 + 0.05f));
                height= (int)(height *  (1 + 0.05f));

                float v1 = lastHeight / (float) height;
                if (mShadowTv != null) {
                    scale = v1;
                    mShadowTv.matrix(scale);
                    mShadowTv.setText(result);
                }
                if (mShadowTv != null) {
                    scale = scale + 0.02f;
                    mShadowTv.matrix(scale);
                    mShadowTv.setText(result);
                }
                break;
        }
    }
    float scale = 1.0f;
    public void addShadowTextView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.shadow_fragment_ll, null);
        mShadowTv = ((ShadowTextView) inflate.findViewById(R.id.shadow_tv));
        mShadowll = ((LinearLayout) inflate.findViewById(R.id.shadow_fragment));
        mBaseRecyclerEmptyContainer.addView(inflate);
    }


}
