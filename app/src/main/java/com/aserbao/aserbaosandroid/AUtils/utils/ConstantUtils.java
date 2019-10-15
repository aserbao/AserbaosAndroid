package com.aserbao.aserbaosandroid.AUtils.utils;

import com.aserbao.aserbaosandroid.R;

import java.util.Random;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class ConstantUtils {

    public static int getDrawable(){
        return drawables[new Random().nextInt(drawables.length)];
    }
    public static int[] drawables = {
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.six,
        R.drawable.seven,
    };
}
