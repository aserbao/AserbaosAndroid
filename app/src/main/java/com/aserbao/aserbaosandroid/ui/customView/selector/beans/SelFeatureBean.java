package com.aserbao.aserbaosandroid.ui.customView.selector.beans;

import java.io.Serializable;

/**
 * @Created time:2021/6/6 2:59 PM
 * @author: aserbao
 * @description: 具体特征
 **/
public class SelFeatureBean implements Serializable {
    //缩略图
    public int thumbnail;
    //特征路径
    public String path = "";

    // 是否被选中？
    public boolean isSel = false;

    public SelFeatureBean(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
