package com.aserbao.aserbaosandroid.functions.how_create_so.create_spot;

import java.util.Vector;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/22 7:22 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.how_create_so.create_spot
 */
public class BeatDetector {
    private long self_ptr;
    static {
        System.loadLibrary("spot_beat_detector");
    }

    public BeatDetector(float bmp_max, float bpm_min) {
        init(bmp_max, bpm_min);
    }

    private native void init(float bmp_max, float bpm_min);

    public native void process(float shorts, float[] mVector);
    public native float getWinVal();
    public native int getBeatCount();
}
