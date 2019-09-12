package com.aserbao.aserbaosandroid.AUtils.utils.phone;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.aserbao.aserbaosandroid.AserbaoApplication;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-09-12 17:04
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.utils.phone
 */
public class APhoneModelUtils {

    public static boolean isOppo(){
        if (getMobileType().equals("OPPO")) { // VIVO测试通过
            return true;
        }  else {
            return false;
        }
    }

    public static boolean isVivo(){
        if (getMobileType().equals("vivo")) { // VIVO测试通过
            return true;
        }  else {
            return false;
        }
    }

    public static boolean isXiaomi(){
        if (getMobileType().equals("Xiaomi")) { // VIVO测试通过
            return true;
        }  else {
            return false;
        }
    }

    private static String getMobileType() {
        return Build.MANUFACTURER;
    }


    /**
     * 微信
     */
    public static final String WECHAT = "com.tencent.mm";
    /**
     * QQ
     */
    public static final String QQ = "com.tencent.mobileqq";
    /**
     * Sina 微博
     */
    public static final String SINA_WEIBO = "com.sina.weibo";
    /**
     * 抖音
     */
    public static final String DOU_YIN = "com.ss.android.ugc.aweme";
    /**
     * 快手
     */
    public static final String KUAI_SHOU = "com.smile.gifmaker";

    /**
     * @return
     */
    public static boolean isWxInstalled() {
        return isAppInstalled(WECHAT);
    }
    /**
     * 判断qq是否可用
     * @return
     */
    public static boolean isQQInstalled() {
        return isAppInstalled(QQ);
    }

    public static boolean isSnapchatInstalled() {
        return isAppInstalled("com.snapchat.android");
    }


    public static boolean isDouYinInstalled(){
        return isAppInstalled(DOU_YIN);
    }
    public static boolean isKuaiShouInstalled(){
        return isAppInstalled(KUAI_SHOU);
    }
    /**
     * 判断手机是否安装新浪微博
     * @return
     */
    public static boolean isWeiboInstalled() {
        return isAppInstalled(SINA_WEIBO);
    }

    public static boolean isBaiduMapInstalled(){
        return isAppInstalled("com.baidu.BaiduMap");
    }

    public static boolean isGaodeMapInstalled(){
        return isAppInstalled("com.autonavi.minimap");
    }

    public static boolean isTencentMapInstalled(){
        return isAppInstalled("com.tencent.map");
    }

    public static boolean isGoogleMapInstalled(){
        return isAppInstalled("com.google.android.apps.maps");
    }

    public static boolean isDianpingInstalled() {
        return isAppInstalled("com.dianping.v1");
    }

    /**
     * qq音乐是否安装
     * @return
     */
    public static boolean isQqMusicInstalled(){
        return isAppInstalled("com.tencent.qqmusic");
    }

    public static boolean isGoogleServiceAvailable() {
//        return isAppInstalled("com.android.vending");
        return isAppInstalled("com.google.android.gms");
    }

    /**
     * 是否有安装某个包的app
     * @param packageName
     * @return
     */
    private static boolean isAppInstalled(String packageName){
        final PackageManager packageManager = AserbaoApplication.getInstance().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
