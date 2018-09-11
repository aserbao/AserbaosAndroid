package com.aserbao.aserbaosandroid.ui.editTexts;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.AutoAdjustSoftHeightActivity;
import com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure.SoftHeightMeasureActivity;

public class EditTextsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("计算软键盘的高度", SoftHeightMeasureActivity.class));
        mClassBeen.add(new ClassBean("editText上移，背景不上移", AutoAdjustSoftHeightActivity.class));

    }


}
