package com.aserbao.aserbaosandroid.ui;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.animation.AnimationActivity;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;
import com.aserbao.aserbaosandroid.ui.customView.CustomViewActivity;
import com.aserbao.aserbaosandroid.ui.texts.TextsActivity;
import com.aserbao.aserbaosandroid.ui.rv.RecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.simpleDraw.SimpleDrawActivity;
import com.aserbao.aserbaosandroid.ui.toasts.ToastActivity;

public class UIActivity extends BaseActivity {



    public void initGetData() {
        mClassBeen.add(new ClassBean("动画效果", AnimationActivity.class));
        mClassBeen.add(new ClassBean("自定义View", CustomViewActivity.class));
        mClassBeen.add(new ClassBean("Android自定义绘制这边走", SimpleDrawActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView的使用", RecyclerViewActivity.class));
        mClassBeen.add(new ClassBean("文本视图的使用", TextsActivity.class));
        mClassBeen.add(new ClassBean("ColorPicker取色器的使用", ColorPickerActivity.class));
        mClassBeen.add(new ClassBean("Toast的使用", ToastActivity.class));
    }

}
