package com.aserbao.aserbaosandroid.functions.aaSmallFunctions;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.functions.aaSmallFunctions.lambda.LambdaActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.aaSmallFunctions.clickEffect.ClickEffectActivity;

/**
 * 小功能点合计
 */
public class SmallFunctionsActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("点击涟漪", ClickEffectActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Lambda 表达式", LambdaActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
