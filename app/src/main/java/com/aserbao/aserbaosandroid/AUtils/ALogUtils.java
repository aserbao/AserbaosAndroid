package com.aserbao.aserbaosandroid.AUtils;

import android.text.TextUtils;
import android.util.Log;

public class ALogUtils {
    private static long mStartTime = 0;

    /**
     * @param tag
     * @param msg
     * @param initTimeState 0表示开始时间，1表示结算时间，2表示不处理时间
     */
    public static void logError(String tag,String msg, int initTimeState){
            if(TextUtils.isEmpty(msg)){
                return;
            }
            if(initTimeState == 1) {
                long l = System.currentTimeMillis() - mStartTime;
                Log.e(tag + "时间测试", msg + " 耗时 = " + String.valueOf(l) + "ms");
            }else{
                if(initTimeState == 0){
                    mStartTime = System.currentTimeMillis();
                }
                Log.e(tag, msg);
            }
    };
}