package com.aserbao.aserbaosandroid.functions.how_create_so.useCmake;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/31
 * email: 1142803753@qq.com
 */
public class AserbaoUtils {
    static {
        System.loadLibrary("aserbao-one-lib");
        System.loadLibrary("aserbao-two-lib");
    }
    public static native String getSimpleInfoFromOne();
    public static native String getSimpleInfoFromTwo();
}
