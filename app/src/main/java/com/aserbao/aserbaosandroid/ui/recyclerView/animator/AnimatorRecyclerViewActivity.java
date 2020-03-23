package com.aserbao.aserbaosandroid.ui.recyclerView.animator;

import android.util.Log;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class AnimatorRecyclerViewActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("删除item"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("删除item"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("添加动画"));
    }

    private static final String TAG = "AnimatorRecyclerViewAct";
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        Log.e(TAG, "itemClickBack: " + position );
        switch (position){
            case 0:
            case 1:
                mCommonAdapter.notifyItemRemoved(position);
                break;
            case 2:
                mCommonAdapter.notifyItemInserted(0);
                break;
        }
    }
}
