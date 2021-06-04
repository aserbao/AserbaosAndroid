package com.aserbao.aserbaosandroid.ui.customView.selector.beans;

import com.aserbao.aserbaosandroid.ui.customView.selector.BorderSelRoundImageView;

/**
 * @Created time:2021/6/4 4:25 PM
 * @author: aserbao
 * @description:
 **/
public class SelBeans {
    // 类型 0表示正常内容，1 表示头部，2表示尾部
    int type = 0;
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
