package com.example.base.base.beans;

import java.util.List;

public class GridViewBean {
    List<String> srcUrl;
    String title;

    public GridViewBean(List<String> srcUrl) {
        this.srcUrl = srcUrl;
    }

    public GridViewBean(List<String> srcUrl, String title) {
        this.srcUrl = srcUrl;
        this.title = title;
    }

    public List<String> getSrcUrl() {
        return srcUrl;
    }

    public String getTitle() {
        return title;
    }
}
