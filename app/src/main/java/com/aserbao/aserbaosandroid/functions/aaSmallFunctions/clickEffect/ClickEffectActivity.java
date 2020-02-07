package com.aserbao.aserbaosandroid.functions.aaSmallFunctions.clickEffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;

/**
 * 控件点击涟漪
 */
public class ClickEffectActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        View view = addLayoutToFrameLayout(R.layout.click_effect_layout, true);
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
