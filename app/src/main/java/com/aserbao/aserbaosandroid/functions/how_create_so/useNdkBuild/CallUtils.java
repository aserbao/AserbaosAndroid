package com.aserbao.aserbaosandroid.functions.how_create_so.useNdkBuild;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/1
 * email: 1142803753@qq.com
 */
public class CallUtils {
    static {
        System.loadLibrary("use_ndk_build");
    }
    public static native String callSimpleInfo();
}
