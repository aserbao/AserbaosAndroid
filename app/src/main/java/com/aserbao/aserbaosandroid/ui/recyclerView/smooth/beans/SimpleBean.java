package com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/28 11:52 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans
 */
public class SimpleBean {
    int coverId;
    String content;

    public SimpleBean(int coverId, String content) {
        this.coverId = coverId;
        this.content = content;
    }

    public int getCoverId() {
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
