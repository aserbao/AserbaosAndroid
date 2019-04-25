package com.aserbao.aserbaosandroid.functions.how_create_so.useNdkBuild;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/1
 * email: this is empty email
 */
public class CallUtils {
    static {
        System.loadLibrary("use_ndk_build");
//        System.loadLibrary("use_ndk_build2");
    }
    public static native String callSimpleInfo();
    public static native String callSimpleInfo2();
}
