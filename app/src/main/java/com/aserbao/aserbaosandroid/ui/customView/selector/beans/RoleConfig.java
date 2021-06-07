package com.aserbao.aserbaosandroid.ui.customView.selector.beans;

import com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory;

import java.io.Serializable;
import java.util.ArrayList;

import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.SEL_FEATURE;

/**
 * @Created time:2021/6/2 5:39 PM
 * @author: aserbao
 * @description: 角色配置
 **/
public class RoleConfig implements Serializable {
    DataProviderFactory.ROLE_FEATURE type;
    // 名字，脸型，发型
    String name;
    // 选中的颜色
    String selColor = "#FFFFFF";
    // 选中的资源，哪一个发型
    String selSource;
    // 详细特征列表
    ArrayList<SelFeatureBean> featureBeans = new ArrayList<>();

    // 是否选中？
    boolean isSel = false;

    public RoleConfig(DataProviderFactory.ROLE_FEATURE type, String name, String selColor, String selSource) {
        this.type = type;
        this.name = name;
        this.selColor = selColor;
        this.selSource = selSource;
        for (int s : SEL_FEATURE) {
            SelFeatureBean featureBean = new SelFeatureBean(s);
            featureBeans.add(featureBean);
        }
    }


    public DataProviderFactory.ROLE_FEATURE getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelColor() {
        return selColor;
    }

    public void setSelColor(String selColor) {
        this.selColor = selColor;
    }

    public String getSelSource() {
        return selSource;
    }

    public void setSelSource(String selSource) {
        this.selSource = selSource;
    }

    public ArrayList<SelFeatureBean> getFeatureBeans() {
        return featureBeans;
    }

    public void setFeatureBeans(ArrayList<SelFeatureBean> featureBeans) {
        this.featureBeans = featureBeans;
    }

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }
}
