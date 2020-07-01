package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.functions.gif;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifDrawable;
/*
 * 作用：
 * @author aserbao
 * @date: on 2020/7/1
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.app.Activity.functions.gif
 */
class GifUtil {

    public static Bitmap getBitmapArrayByGif(Context context, String assertPath, int index) {
        try {
            ArrayList<Bitmap> list = new ArrayList<>();
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), assertPath);//代表android中assert的gif文件名
            int totalCount = gifFromAssets.getNumberOfFrames();
            if (totalCount < index) {
                index = totalCount - 1;
            }
            return gifFromAssets.seekToFrameAndGet(index);
        } catch (Exception e) {
            return null;
        }
    }


}
