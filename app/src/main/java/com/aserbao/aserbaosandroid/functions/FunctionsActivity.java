package com.aserbao.aserbaosandroid.functions;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.events.EventDealActivity;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.JumpSystemSettingActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;

public class FunctionsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("不同手机跳转到设置界面", JumpSystemSettingActivity.class));
        mClassBeen.add(new ClassBean("事件处理", EventDealActivity.class));

    }

}
