package com.aserbao.aserbaosandroid.AUtils.data;

import com.example.base.base.beans.SelBean;
import com.example.base.base.beans.VHSelecteBean;

import java.util.ArrayList;
import java.util.List;

public class AViewDataUtils {

    public static List<SelBean> getGravity(){
        List<SelBean> selBeans = new ArrayList<>();
        selBeans.add(new SelBean("bottom",50));
        selBeans.add(new SelBean("center",11));
        selBeans.add(new SelBean("center_horizontal",1));
        selBeans.add(new SelBean("center_vertical",10));
        selBeans.add(new SelBean("clip_horizontal",8));
        selBeans.add(new SelBean("clip_vertical",80));
        selBeans.add(new SelBean("end",800005));
        selBeans.add(new SelBean("fill",77));
        selBeans.add(new SelBean("fill_horizontal",7));
        selBeans.add(new SelBean("fill_vertical",70));
        selBeans.add(new SelBean("left",3));
        selBeans.add(new SelBean("right",5));
        selBeans.add(new SelBean("start",800003));
        selBeans.add(new SelBean("top",30));
        return selBeans;
    }
    
    
    public static List<SelBean> getGridViewStretchMode(){
        List<SelBean> selBeans = new ArrayList<>();
        selBeans.add(new SelBean("columnWidth",2));
        selBeans.add(new SelBean("none",0));
        selBeans.add(new SelBean("spacingWidth",1));
        selBeans.add(new SelBean("spacingWidthUniform",3));
        return selBeans;
    }
    
    
}




