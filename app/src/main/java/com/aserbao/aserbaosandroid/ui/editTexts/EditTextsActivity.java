package com.aserbao.aserbaosandroid.ui.editTexts;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure.SoftHeightMeasureActivity;

public class EditTextsActivity extends BaseActivity {

    @Override
    public int setContentView() {
        return R.layout.base_activity;
    }

    public void initGetData() {
        mClassBeen.add(new ClassBean("计算软键盘的高度", SoftHeightMeasureActivity.class));

    }


}
