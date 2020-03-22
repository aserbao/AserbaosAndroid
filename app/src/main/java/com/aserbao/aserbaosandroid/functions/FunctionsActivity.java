package com.aserbao.aserbaosandroid.functions;

import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.example.base.base.BaseRecyclerViewActivity;
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
import com.aserbao.aserbaosandroid.functions.regex.RegexActivity;
import com.aserbao.aserbaosandroid.functions.sensors.SensorsActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class FunctionsActivity extends BaseRecyclerViewActivity {

    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("跳转其他界面处理", Jump2OtherActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("常用工具类使用", AUtilsRealizeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("不同手机跳转到设置界面", JumpSystemSettingActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("事件处理", EventDealActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("数据库的使用", DataBaseActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("图片的处理", ImagePhotoShopActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("so库文件的生成", HowCreateSoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("传感器", SensorsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("小兴趣", HobbiesAndInterestsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("常用系统监听", ListenerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Launch", LaunchActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("网络状态监听", NetWorkNotification.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("关于Bitmap", AboutBitmapActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("正则表达式", RegexActivity.class));

    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}

}
