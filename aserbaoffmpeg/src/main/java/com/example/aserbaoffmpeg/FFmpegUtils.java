package com.example.aserbaoffmpeg;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/8
 * email: 1142803753@qq.com
 */
public class FFmpegUtils {
    private String s = " 这是second_branch的初始状态";
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
