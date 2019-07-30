package com.aserbao.aserbaosandroid.commonData;

import android.os.Environment;

public class StaticFinalValues {


    public static final String IMAGE_URL = "https://avatars1.githubusercontent.com/u/21996952?s=460&v=4";
    public static final String VIDEO_URL = "video_url";
    public static final String STORAGE_TEMP_VIDEO_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao.mp4";
    public static final String STORAGE_TEMP_FILE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao";
    public static final String STORAGE_TEMP_APK_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao.apk";

    //=================================Handler
    public static final int EMPTY = 0;


    //=================================bundler
    public static final int KEY = 1;
    public static final int INDEX_TAG =100;
    public static final String TYPE = "type";

    // =================================direction
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    public static final int EXPLORE = 4;
    public static final int FADE = 5;
    public static final int MOVE = 6;
    public static final int NO_TRANSITION = 7;



    //=================================RecyclerView
    public static final int HEAD = 0;
    public static final int NOCONTENT = 1;
    public static final int CONTENT = 4;
    public static final int CONTENT1 = 5;
    public static final int CONTENT2 = 6;
    public static final int FOOT = 8;


}
