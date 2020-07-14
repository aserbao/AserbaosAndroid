package com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Pair;

import androidx.annotation.NonNull;

/**
 * util to get:
 * dp convert px
 * screen's width/height
 * Created by hanks on 15-12-19.
 */
public final class DisplayUtils {

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static int dp2px(float dp) {
        return Math.round(dp * getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    @NonNull
    public static Pair<Integer, Integer> getAspectRatio(int width, int height) {
        // http://stackoverflow.com/questions/7442206/how-to-calculate-the-aspect-ratio-by-a-given-factor

        int factor = greatestCommonFactor(width, height);

        int widthRatio = width / factor;
        int heightRatio = height / factor;

        return new Pair<>(widthRatio, heightRatio);
    }

    private static int greatestCommonFactor(int width, int height) {
        return (height == 0) ? width : greatestCommonFactor(height, width % height);
    }

}
