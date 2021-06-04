package com.aserbao.aserbaosandroid.ui.customView.selector.beans;

import com.aserbao.aserbaosandroid.ui.customView.selector.BorderSelRoundImageView;

/**
 * @Created time:2021/6/4 4:25 PM
 * @author: aserbao
 * @description:
 **/
public class SelBeans {

    public String color;
    public boolean isSel = false;
    public int resId;//图片
    public String path;

    public SelBeans(String color) {
        this.color = color;
    }

    public SelBeans(int resId) {
        this.resId = resId;
    }
}
