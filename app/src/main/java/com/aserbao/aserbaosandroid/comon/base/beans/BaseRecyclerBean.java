package com.aserbao.aserbaosandroid.comon.base.beans;

import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

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
    String extra_info;      //补充信息
    int tag = -1;           //标记
    private Class<?> clazz; //跳转的类
    int imageSrc;           //背景图片地址
    int viewType = StaticFinalValues.VIEW_HOLDER_TEXT;

    public BaseRecyclerBean(int viewType,String name) {
        this.name = name;
        this.viewType = viewType;
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

    public BaseRecyclerBean(int viewType,String extra_info,int tag) {
        this.viewType = viewType;
        this.extra_info = extra_info;
        this.tag = tag;
    }

    public BaseRecyclerBean(String name, Class<?> clazz, int tag) {
        this.name = name;
        this.tag = tag;
        this.clazz = clazz;
        this.viewType = StaticFinalValues.VIEW_HOLDER_CLASS;
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
}
