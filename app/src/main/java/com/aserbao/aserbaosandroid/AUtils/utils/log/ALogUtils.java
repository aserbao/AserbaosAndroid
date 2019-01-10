package com.aserbao.aserbaosandroid.AUtils.utils.log;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 功能:日志输出工具类
 * @author aserbao
 * @date : On 2019/1/3 11:25 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.utils
 * @Copyright: 个人版权所有
 */
public class ALogUtils {
    private static long mStartTime = 0;
    public static boolean mLogEnable = true;
    public static final String PREFIX = "LogUtils--";

    private static String mClassname;
    private static ArrayList<String> mMethods;

    static {
        mClassname = ALogUtils.class.getName();
        mMethods = new ArrayList<>();

        Method[] ms = ALogUtils.class.getDeclaredMethods();
        for (Method m : ms) {
            mMethods.add(m.getName());
        }
    }

    /**
     * @param tag
     * @param msg
     * @param initTimeState 0表示开始时间，1表示结算时间，2表示不处理时间
     */
    public static void logErrorTime(String tag, String msg, int initTimeState){
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

    public static void init(boolean logEnable) {
        mLogEnable = logEnable;
    }

    public static void d(String tag, String msg) {
        if (mLogEnable) {
            Log.d(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void e(String tag, String msg) {
        if (mLogEnable) {
            Log.e(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (mLogEnable) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (mLogEnable) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void v(String tag, String msg) {
        if (mLogEnable) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }
    }


    public static void d(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], content[1]);
        }
    }

    public static void e(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], content[1]);
        }
    }

    public static void i(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], content[1]);
        }
    }

    public static void i() {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber("");
            Log.i(content[0], content[1]);
        }
    }

    public static void w(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], content[1]);
        }
    }

    public static void v(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], content[1]);
        }
    }

    public static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassname.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String message = new StringBuilder(st.getClassName().substring(b)).append("->").append(st.getMethodName())
                            .append("():").append(st.getLineNumber()).append(msg).toString();
                    return message;
                }

            }
        } catch (Exception e) {

        }
        return msg;
    }

    public static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassname.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = PREFIX + st.getClassName().substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    String[] content = new String[]{TAG, message};
                    return content;
                }

            }
        } catch (Exception e) {

        }
        return new String[]{"universal tag", msg};
    }


}