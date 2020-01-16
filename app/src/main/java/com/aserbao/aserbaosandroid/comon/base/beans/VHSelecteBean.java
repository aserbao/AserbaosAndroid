package com.aserbao.aserbaosandroid.comon.base.beans;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:27
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.beans
 */
public class VHSelecteBean extends VHBaseBean {
    int defaultPosition;// 默认选中位置
    List<Integer> mIntList;

    public VHSelecteBean(String mDescription, int mTag, int defaultPosition, List<Integer> mIntList) {
        super(mDescription, mTag);
        this.defaultPosition = defaultPosition;
        this.mIntList = mIntList;
    }

    public int getDefaultPosition() {
        return defaultPosition;
    }

    public void setDefaultPosition(int defaultPosition) {
        this.defaultPosition = defaultPosition;
    }

    public List<Integer> getmIntList() {
        return mIntList;
    }

    public void setmIntList(List<Integer> mIntList) {
        this.mIntList = mIntList;
    }
}
