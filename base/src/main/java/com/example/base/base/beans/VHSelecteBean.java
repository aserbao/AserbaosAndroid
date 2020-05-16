package com.example.base.base.beans;

import com.example.base.base.interfaces.ISelItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 19:27
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.beans
 */
public class VHSelecteBean {
    String desc;
    SelBean defaultSelBean;
    List<SelBean> classBean = new ArrayList<>();
    ISelItemListener iSelItemListener;

    public VHSelecteBean(String desc, List<SelBean> classBeen, ISelItemListener iSelItemListener) {
        this.desc = desc;
        this.classBean = classBeen;
        this.iSelItemListener = iSelItemListener;
    }

    public VHSelecteBean(String desc, SelBean defaultSelBean, List<SelBean> classBean, ISelItemListener iSelItemListener) {
        this.desc = desc;
        this.defaultSelBean = defaultSelBean;
        this.classBean = classBean;
        this.iSelItemListener = iSelItemListener;
    }

    public String getDesc() {
        return desc;
    }

    public List<SelBean> getClassBean() {
        return classBean;
    }

    public ISelItemListener getiSelItemListener() {
        return iSelItemListener;
    }


}
