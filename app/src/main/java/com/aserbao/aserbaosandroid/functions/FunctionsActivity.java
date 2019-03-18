package com.aserbao.aserbaosandroid.functions;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.database.DataBaseActivity;
import com.aserbao.aserbaosandroid.functions.events.EventDealActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.HobbiesAndInterestsActivity;
import com.aserbao.aserbaosandroid.functions.how_create_so.HowCreateSoActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.ImagePhotoShopActivity;
import com.aserbao.aserbaosandroid.functions.jump2OtherApp.Jump2OtherActivity;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.JumpSystemSettingActivity;
import com.aserbao.aserbaosandroid.functions.sensors.SensorsActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class FunctionsActivity extends BaseActivity {

    public void initGetData() {
        mClassBeen.add(new ClassBean("跳转其他界面处理", Jump2OtherActivity.class));
        mClassBeen.add(new ClassBean("常用工具类使用", AUtilsRealizeActivity.class));
        mClassBeen.add(new ClassBean("不同手机跳转到设置界面", JumpSystemSettingActivity.class));
        mClassBeen.add(new ClassBean("事件处理", EventDealActivity.class));
        mClassBeen.add(new ClassBean("数据库的使用", DataBaseActivity.class));
        mClassBeen.add(new ClassBean("图片的处理", ImagePhotoShopActivity.class));
        mClassBeen.add(new ClassBean("so库文件的生成", HowCreateSoActivity.class));
        mClassBeen.add(new ClassBean("传感器", SensorsActivity.class));
        mClassBeen.add(new ClassBean("小兴趣", HobbiesAndInterestsActivity.class));

    }

}
