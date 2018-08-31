package com.aserbao.aserbaosandroid.ui;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;
import com.aserbao.aserbaosandroid.ui.editTexts.EditTextsActivity;
import com.aserbao.aserbaosandroid.ui.rv.RecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.textViews.TextViewActivity;
import com.aserbao.aserbaosandroid.ui.toasts.ToastActivity;

public class UIActivity extends BaseActivity {



    public void initGetData() {
        mClassBeen.add(new ClassBean("RecyclerView的使用", RecyclerViewActivity.class));
        mClassBeen.add(new ClassBean("EditText的使用", EditTextsActivity.class));
        mClassBeen.add(new ClassBean("TextView的使用", TextViewActivity.class));
        mClassBeen.add(new ClassBean("ColorPicker取色器的使用", ColorPickerActivity.class));
        mClassBeen.add(new ClassBean("Toast的使用", ToastActivity.class));

    }

}
