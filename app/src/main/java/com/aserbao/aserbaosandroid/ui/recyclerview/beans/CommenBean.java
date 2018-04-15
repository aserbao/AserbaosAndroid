package com.aserbao.aserbaosandroid.ui.recyclerview.beans;

/**
 * Created by aserbao on 2018 2018/4/15.22:56
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class CommenBean {
    private String picUrl;
    private String userName;

    public CommenBean(String picUrl, String userName) {
        this.picUrl = picUrl;
        this.userName = userName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
