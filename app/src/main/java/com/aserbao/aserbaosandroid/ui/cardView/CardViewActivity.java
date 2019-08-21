package com.aserbao.aserbaosandroid.ui.cardView;

import android.os.Bundle;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.cardView.simpleCardView.SimpleCardViewActivity;

public class CardViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("CardView的基本使用", SimpleCardViewActivity.class));
    }
}
