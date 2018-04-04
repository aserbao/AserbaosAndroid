package com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee;

/**
 * Created by aserbao on 2018 2018/4/4.8:42
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class Cream extends Material {

    private Material mMaterial;

    public Cream(Material material) {
        mMaterial = material;
    }

    @Override
    public int cost() {
        return 1 + mMaterial.cost();
    }

    @Override
    public String description() {
        return "奶油" + mMaterial.description();
    }
}
