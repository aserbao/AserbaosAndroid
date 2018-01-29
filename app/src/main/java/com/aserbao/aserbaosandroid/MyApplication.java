package com.aserbao.aserbaosandroid;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * description:
 * Created by aserbao on 2018/1/29.
 */


public class MyApplication extends Application {
    private static Context mContext;
    public static int screenWidth;
    public static int screenHeight;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DisplayMetrics mDisplayMetrics = getApplicationContext().getResources()
                .getDisplayMetrics();
        screenWidth = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;
    }

    public static Context getContext() {
        return mContext;
    }
}
