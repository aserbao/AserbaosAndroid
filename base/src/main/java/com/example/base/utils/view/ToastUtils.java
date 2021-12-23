package com.example.base.utils.view;

import android.content.Context;
import android.widget.Toast;

import com.example.base.utils.log.ALogUtils;

/**
* toast工具
*/
public class ToastUtils {
    private static Context mAppContext = null;

    public static void init(Context context) {
        mAppContext = context;
    }


    public static void show(String msg) {
        if (null == mAppContext) {
            ALogUtils.d("ToastUtils not inited with Context");
            return;
        }
        Toast.makeText(mAppContext, msg, Toast.LENGTH_SHORT).show();
    }



}
