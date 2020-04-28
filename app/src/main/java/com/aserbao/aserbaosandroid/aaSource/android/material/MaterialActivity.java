package com.aserbao.aserbaosandroid.aaSource.android.material;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.material.bottomSheetDialog.BottomSheetDialogActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.button.MaterialButtonActivity;
import com.aserbao.aserbaosandroid.aaSource.android.material.floatView.FloatWindowActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class MaterialActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("MaterialButtonActivity", MaterialButtonActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("About BottomSheet", BottomSheetDialogActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("悬浮窗", FloatWindowActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
