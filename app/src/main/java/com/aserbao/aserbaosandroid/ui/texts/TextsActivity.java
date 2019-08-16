package com.aserbao.aserbaosandroid.ui.texts;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.EditTextsActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.TextViewsActivity;

public class TextsActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("TextView的使用", TextViewsActivity.class));
        mClassBeen.add(new ClassBean("EditText的使用", EditTextsActivity.class));
    }
}
