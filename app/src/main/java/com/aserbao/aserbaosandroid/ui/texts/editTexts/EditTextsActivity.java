package com.aserbao.aserbaosandroid.ui.texts.editTexts;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.FullDragDemoActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen.AutoAdjustSoftHeightActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen.OneEditTextFullScreenActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext.CustomEditTextActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.others.OthersActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure.SoftHeightMeasureActivity;

public class EditTextsActivity extends BaseRecyclerViewActivity {

    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("系统属性值区别", SoftHeightMeasureActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("editText上移，背景不上移,将布局往下移", OneEditTextFullScreenActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("editText上移，背景不上移，底部添加布局", AutoAdjustSoftHeightActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("全屏底部上移加按钮拖动", FullDragDemoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("自定义EditText", CustomEditTextActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("其他属性", OthersActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
