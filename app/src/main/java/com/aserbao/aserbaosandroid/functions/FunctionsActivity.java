package com.aserbao.aserbaosandroid.functions;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.DataBaseActivity;
import com.aserbao.aserbaosandroid.functions.events.EventDealActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.ImagePhotoShopActivity;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.JumpSystemSettingActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;

public class FunctionsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("常用工具类使用", AUtilsRealizeActivity.class));
        mClassBeen.add(new ClassBean("不同手机跳转到设置界面", JumpSystemSettingActivity.class));
        mClassBeen.add(new ClassBean("事件处理", EventDealActivity.class));
        mClassBeen.add(new ClassBean("数据库的使用", DataBaseActivity.class));
        mClassBeen.add(new ClassBean("图片的处理", ImagePhotoShopActivity.class));

    }

}
