package com.example.base.base.beans;

import java.util.List;

public class GridViewBean {
    List<String> srcUrl;
    String title;

    int columnWidth;
    int gravity;
    int horizontalSpacing;
    int numColumns;
    int stretchMode;
    int verticalSpacing;

    public GridViewBean(List<String> srcUrl, String title, int columnWidth, int gravity, int horizontalSpacing, int numColumns, int stretchMode, int verticalSpacing) {
        this.srcUrl = srcUrl;
        this.title = title;
        this.columnWidth = columnWidth;
        this.gravity = gravity;
        this.horizontalSpacing = horizontalSpacing;
        this.numColumns = numColumns;
        this.stretchMode = stretchMode;
        this.verticalSpacing = verticalSpacing;
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


    public int getColumnWidth() {
        return columnWidth;
    }

    public int getGravity() {
        return gravity;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getStretchMode() {
        return stretchMode;
    }

    public int getVerticalSpacing() {
        return verticalSpacing;
    }
}
