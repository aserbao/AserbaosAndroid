package com.example.base.utils.screen;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.view.ViewConfiguration;

import com.example.base.utils.phone.APhoneModelUtils;


/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-09-12 17:01
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.utils.screen
 */
public class AScreenUtils {

    public static boolean isNavigationBarShowing(Context var0) {
        if(!hasNavigationBar(var0)) {
            return false;
        } else {
            if(Build.VERSION.SDK_INT >= 17) {
                String var1;
                if(APhoneModelUtils.isXiaomi()) {
                    var1 = "force_fsg_nav_bar";
                    if(Settings.Global.getInt(var0.getContentResolver(), var1, 0) != 0) {
                        return false;
                    }

                    return true;
                }

                if(APhoneModelUtils.isVivo()) {
                    var1 = "navigation_gesture_on";
                    if(Settings.Secure.getInt(var0.getContentResolver(), var1, 0) != 0) {
                        return true;
                    }

                    return false;
                }
            }

            return true;
        }
    }

    public static boolean hasNavigationBar(Context var0) {
        boolean var1 = false;
        int var2;
        Resources var4;
        if((var2 = (var4 = var0.getResources()).getIdentifier("config_showNavigationBar", "bool", "android")) > 0) {
            var1 = var4.getBoolean(var2);
        }

        try {
            Class var5;
            String var6 = (String)(var5 = Class.forName("android.os.SystemProperties")).getMethod("get", new Class[]{String.class}).invoke(var5, new Object[]{"qemu.hw.mainkeys"});
            if("1".equals(var6)) {
                var1 = false;
            } else if("0".equals(var6)) {
                var1 = true;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }

    public static int getNavigationBarHeight(Context var0) {
        boolean var1 = ViewConfiguration.get(var0).hasPermanentMenuKey();
        int var2;
        return (var2 = var0.getResources().getIdentifier("navigation_bar_height", "dimen", "android")) > 0 && !var1?var0.getResources().getDimensionPixelSize(var2):0;
    }


    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height","dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
