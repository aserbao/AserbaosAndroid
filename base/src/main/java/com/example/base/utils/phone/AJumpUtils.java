package com.example.base.utils.phone;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/22 8:39 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.utils.phone
 */
public class AJumpUtils {
    public static void jumpToTikTok(Activity activity){
        Intent intentDouYin = new Intent();
        ComponentName componentNameDouYin = new ComponentName("com.ss.android.ugc.aweme", "com.ss.android.ugc.aweme.main.MainActivity");
        intentDouYin.setComponent(componentNameDouYin);
        intentDouYin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentDouYin);
    }

    public static void jumpToPackage(Activity activity,String pkg,String cls){
        Intent intentDouYin = new Intent();
        ComponentName componentNameDouYin = new ComponentName(pkg, cls);
        intentDouYin.setComponent(componentNameDouYin);
        intentDouYin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentDouYin);
    }


    public static void jumpToKuaiShou(Activity activity){
        Intent intentKuaiShou = new Intent();
        ComponentName componentNameKuaiShou = new ComponentName("com.smile.gifmaker", "com.yxcorp.gifshow.HomeActivity");
        intentKuaiShou.setComponent(componentNameKuaiShou);
        intentKuaiShou.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentKuaiShou);
    }

    public static void jumpToWeChat(Activity activity){
        Intent intentWeChat = new Intent();
        ComponentName componentNameKuaiShou = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intentWeChat.setComponent(componentNameKuaiShou);
        intentWeChat.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentWeChat);
    }
}
