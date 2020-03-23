package com.example.base.base.beans;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:30
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.beans
 */
public class VHBaseBean {
    String mDescription;//描述
    int mTag;  // 标记

    public VHBaseBean(String mDescription, int mTag) {
        this.mDescription = mDescription;
        this.mTag = mTag;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmTag() {
        return mTag;
    }

    public void setmTag(int mTag) {
        this.mTag = mTag;
    }
}
