package com.aserbao.aserbaosandroid.functions;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.AboutBitmapActivity;
import com.aserbao.aserbaosandroid.functions.database.DataBaseActivity;
import com.aserbao.aserbaosandroid.functions.events.EventDealActivity;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.HobbiesAndInterestsActivity;
import com.aserbao.aserbaosandroid.functions.how_create_so.HowCreateSoActivity;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.ImagePhotoShopActivity;
import com.aserbao.aserbaosandroid.functions.jump2OtherApp.Jump2OtherActivity;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.JumpSystemSettingActivity;
import com.aserbao.aserbaosandroid.functions.launch.LaunchActivity;
import com.aserbao.aserbaosandroid.functions.listener.ListenerActivity;
import com.aserbao.aserbaosandroid.functions.notifacations.networks.NetWorkNotification;
import com.aserbao.aserbaosandroid.functions.sensors.SensorsActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

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
        mClassBeen.add(new ClassBean("常用系统监听", ListenerActivity.class));
        mClassBeen.add(new ClassBean("Launch", LaunchActivity.class));
        mClassBeen.add(new ClassBean("网络状态监听", NetWorkNotification.class));
        mClassBeen.add(new ClassBean("关于Bitmap", AboutBitmapActivity.class));

    }

}
