package com.aserbao.aserbaosandroid.functions.how_create_so.useCmake;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/31
 * email: this is empty email
 */
public class AserbaoUtils {
    static {
        System.loadLibrary("use_cmake_build");
//        System.loadLibrary("use_cmake_build2");
    }
    public static native String getSimpleInfoFromOne();
    public static native String getSimpleInfoFromTwo();
}
