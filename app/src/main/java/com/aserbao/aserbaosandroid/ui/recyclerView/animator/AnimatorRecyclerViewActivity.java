package com.aserbao.aserbaosandroid.ui.recyclerView.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class AnimatorRecyclerViewActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("删除item"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("删除item"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("添加动画"));
    }

    private static final String TAG = "AnimatorRecyclerViewAct";
    @Override
    public void itemClickBack(View view, int position) {
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
