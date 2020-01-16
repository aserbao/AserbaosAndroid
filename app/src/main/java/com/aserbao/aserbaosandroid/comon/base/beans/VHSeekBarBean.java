package com.aserbao.aserbaosandroid.comon.base.beans;

import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRvItemInSeekBarListener;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 17:20
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.beans
 */
public class VHSeekBarBean {
    String mDescription;//描述
    int mTag;  // 标记
    int mMax = 100; // 最大范围
    int mDefaultPosition;//默认数字
    IBaseRvItemInSeekBarListener mIBaseRvItemInSeekBarListener;

    public VHSeekBarBean(String des, int tag, int max, int defaultPosition, IBaseRvItemInSeekBarListener iBaseRvItemInSeekBarListener) {
        this(des,tag,iBaseRvItemInSeekBarListener);
        mMax = max;
        mDefaultPosition = defaultPosition;
    }

    public VHSeekBarBean(String description, int tag, IBaseRvItemInSeekBarListener iBaseRvItemInSeekBarListener) {
        mDescription = description;
        mTag = tag;
        mIBaseRvItemInSeekBarListener = iBaseRvItemInSeekBarListener;
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

    public int getmMax() {
        return mMax;
    }

    public void setmMax(int mMax) {
        this.mMax = mMax;
    }

    public int getmDefaultPosition() {
        return mDefaultPosition;
    }

    public void setmDefaultPosition(int mDefaultPosition) {
        this.mDefaultPosition = mDefaultPosition;
    }

    public IBaseRvItemInSeekBarListener getmIBaseRvItemInSeekBarListener() {
        return mIBaseRvItemInSeekBarListener;
    }

    public void setmIBaseRvItemInSeekBarListener(IBaseRvItemInSeekBarListener mIBaseRvItemInSeekBarListener) {
        this.mIBaseRvItemInSeekBarListener = mIBaseRvItemInSeekBarListener;
    }
}
