package com.aserbao.aserbaosandroid.ui.canvas.shadows;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.canvas.shadows.views.ShadowTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShadowsActivity extends BaseRecyclerViewActivity {


    ShadowTextView mShadowTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("缩小", 3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("增大", 4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带阴影的TextView", 1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带阴影的Fragment", 2));
        mBaseRecyclerEmptyContainer.setVisibility(View.GONE);
    }

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
                if (mShadowTv != null) {
                    scale = scale - 0.02f;
                    mShadowTv.matrix(scale);
                }
                break;
            case 4:
                if (mShadowTv != null) {
                    scale = scale + 0.02f;
                    mShadowTv.matrix(scale);
                }
                break;
        }
    }
    float scale = 1.0f;
    public void addShadowTextView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.shadow_fragment_ll, null);
        mShadowTv = ((ShadowTextView) inflate.findViewById(R.id.shadow_tv));
        mBaseRecyclerEmptyContainer.addView(inflate);
    }


}
