package com.example.base.base.beans;

import com.example.base.utils.data.StaticFinalValues;

import java.io.Serializable;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/19 4:51 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base.beans
 */
public class BaseRecyclerBean implements Serializable {
    String name;            //名字
    int tag = -1;           //标记
    private Class<?> clazz; //跳转的类
    int imageSrc;           //背景图片地址
    int viewType = StaticFinalValues.VIEW_HOLDER_TEXT;
    VHSeekBarBean mVHSeekBarBean;
    GridViewBean mGridViewBean;

    /**
     * 顶部文字提示
     * @param viewType
     * @param name
     */
    public BaseRecyclerBean(int viewType,String name) {
        this.name = name;
        this.viewType = viewType;
    }

    public BaseRecyclerBean(int tag,int viewType, GridViewBean mGridViewBean) {
        this.viewType = viewType;
        this.mGridViewBean = mGridViewBean;
        this.tag = tag;
    }

    public BaseRecyclerBean(String name) {
        this.name = name;
    }


    public BaseRecyclerBean(String name, int tag) {
        this.name = name;
        this.tag = tag;
        this.viewType = StaticFinalValues.VIEW_HOLDER_TEXT;
    }

    public BaseRecyclerBean(int imageSrc,int viewType) {
        this.imageSrc = imageSrc;
        this.viewType = viewType;
    }

    public BaseRecyclerBean(int imageSrc,int viewType,int tag) {
        this.imageSrc = imageSrc;
        this.viewType = viewType;
        this.tag = tag;
    }

    public BaseRecyclerBean(int viewType,String name,int tag) {
        this.viewType = viewType;
        this.name = name;
        this.tag = tag;
    }

    public BaseRecyclerBean(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
        this.viewType = StaticFinalValues.VIEW_HOLDER_CLASS;
    }

    public BaseRecyclerBean(VHSeekBarBean vHSeekBarBean) {
        mVHSeekBarBean = vHSeekBarBean;
        this.viewType = StaticFinalValues.VIEW_SEEK_BAR;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VHSeekBarBean getmVHSeekBarBean() {
        return mVHSeekBarBean;
    }

    public GridViewBean getmGridViewBean() {
        return mGridViewBean;
    }
}
