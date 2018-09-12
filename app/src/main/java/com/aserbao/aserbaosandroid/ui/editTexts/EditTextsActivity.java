package com.aserbao.aserbaosandroid.ui.editTexts;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.fullScreen.AutoAdjustSoftHeightActivity;
import com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.fullScreen.OneEditTextFullScreenActivity;
import com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure.SoftHeightMeasureActivity;

public class EditTextsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("计算软键盘的高度", SoftHeightMeasureActivity.class));
        mClassBeen.add(new ClassBean("editText上移，背景不上移,将布局往下移", OneEditTextFullScreenActivity.class));
        mClassBeen.add(new ClassBean("editText上移，背景不上移，底部添加布局", AutoAdjustSoftHeightActivity.class));

    }


}
