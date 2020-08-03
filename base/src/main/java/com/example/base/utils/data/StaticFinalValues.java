package com.example.base.utils.data;

import android.os.Environment;

public class StaticFinalValues {

    //=============logo===============
    public  static final String LOGO_FROM_ASERBAO = "learn android with aserbao";
    //-====================path
    public static final String IMAGE_URL = "https://avatars1.githubusercontent.com/u/21996952?s=460&v=4";
    public static final String VIDEO_URL = "video_url";
//    /storage/emulated/0/
    public static final String STORAGE_TEMP_VIDEO_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao.mp4";
    public static final String STORAGE_TEMP_VIDEO_PATH2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao2.mp4";
    public static final String STORAGE_TEMP_FILE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao";
    public static final String STORAGE_ZIP_FILE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao/zip";
    public static final String STORAGE_UNZIP_FILE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao/unzip";
    public static final String STORAGE_TEMP_APK_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aserbao.apk";
    public static final String MUSIC_PATH_NAME = Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.mp3";


    //=================================Handler
    public static final int EMPTY = 0;


    //=================================bundler
    public static final int KEY = 1;
    public static final int INDEX_TAG =100;
    public static final String TYPE = "type";
    public static final String WHICH_INTERPOLATOR = "which_interpolator";
    public static final String BUNDLE = "bundle";
    public static final String POSITION = "position";
    public static final String DRAWABLE_RESID = "drawable_resid";
    public static final String FLAG = "flag";
    public static final String STRING = "string";

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

    //-------------type---------------
    public static final int VIEW_HOLDER_HEAD = 99;
    public static final int VIEW_HOLDER_TEXT = 100;
    public static final int VIEW_HOLDER_IMAGE_100H = 101;
    public static final int VIEW_HOLDER_CIRCLE_IMAGE_ITEM = 1001;
    public static final int VIEW_FULL_IMAGE_ITEM = 1002;
    public static final int VIEW_HOLDER_CLASS = 102;
    public static final int VIEW_BLEND_MODE = 103;
    public static final int VIEW_SEEK_BAR = 104;
    public static final int VIEW_SELECTE_POSITION = 105;
    public static final int VIEW_GRID_VIEW_ITME = 106;


    //--------------reqeestCode--------------
    public static final int COME_FROM_A_SHARE_MODULE_ACTIVITY = 100;
    public static final int COME_FROM_B_SHARE_MODULE_ACTIVITY = 101;
    public static final int COME_FROM_REQUEST_CODE_TAKE_VIDEO = 102;


    //--------------baseRecyclerView-----------
    public static final int LINEAR_LAYOUTMANAGER_HORIZONTAL = 0;
    public static final int LINEAR_LAYOUTMANAGER_VERTICAL = 1;
    public static final int LINEAR_LAYOUTMANAGER = 100;
    public static final int GRID_LAYOUTMANAGER = 101;


    //---------------interpolator-------------
    public static final int AccelerateDecelerateInterpolator = 0;
    public static final int AccelerateInterpolator = 1;
    public static final int AnticipateInterpolator = 2;
    public static final int AnticipateOvershootInterpolator = 3;
    public static final int BounceInterpolator = 4;
    public static final int CycleInterpolator = 5;
    public static final int DecelerateInterpolator = 6;
    public static final int LinearInterpolator = 7;
    public static final int OvershootInterpolator = 8;
    public static final int PathInterpolator = 9;
    public static final int PathMotion = 10;
    public static final int AcrMotion = 11;





    public static final String MAX_NUMBER = "max_number";
    public static final String RESULT_PICK_VIDEO = "result_pick_video";


}
