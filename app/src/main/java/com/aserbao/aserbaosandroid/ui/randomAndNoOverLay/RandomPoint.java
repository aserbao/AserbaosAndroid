package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay;

import android.graphics.Color;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/9 11:13 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.randomAndNoOverLay
 * @Copyright: 个人版权所有
 */
public class RandomPoint {
    public int x;
    public int y;
    public int radius;
    public int color;
    public RandomPoint centerPoint;


    public RandomPoint(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }
}
