package com.aserbao.aserbaosandroid.ui.texts.editTexts;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.FullDragDemoActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen.AutoAdjustSoftHeightActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen.OneEditTextFullScreenActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext.CustomEditTextActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.others.OthersActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure.SoftHeightMeasureActivity;

public class EditTextsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("系统属性值区别", SoftHeightMeasureActivity.class));
        mClassBeen.add(new ClassBean("editText上移，背景不上移,将布局往下移", OneEditTextFullScreenActivity.class));
        mClassBeen.add(new ClassBean("editText上移，背景不上移，底部添加布局", AutoAdjustSoftHeightActivity.class));
        mClassBeen.add(new ClassBean("全屏底部上移加按钮拖动", FullDragDemoActivity.class));
        mClassBeen.add(new ClassBean("自定义EditText", CustomEditTextActivity.class));
        mClassBeen.add(new ClassBean("其他属性", OthersActivity.class));
    }
}
