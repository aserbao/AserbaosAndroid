package com.example.aserbaoffmpeg;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/8
 * email: this is empty email
 */
public class FFmpegUtils {
    static {
        System.loadLibrary("avcodec");
        System.loadLibrary("avfilter");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");
        System.loadLibrary("ffmpegdemo");
    }
    public static native String avformatInfo();
    public static native String avcodecInfo();
    public static native String avfilterInfo();
    public static native String configurationInfo();

}
