package com.aserbao.aserbaosandroid.ui.texts;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.EditTextsActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.TextViewsActivity;

public class TextsActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("TextView的使用", TextViewsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("EditText的使用", EditTextsActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
