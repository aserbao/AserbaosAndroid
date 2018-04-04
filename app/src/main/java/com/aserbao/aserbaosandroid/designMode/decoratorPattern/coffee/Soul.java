package com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee;

/**
 * Created by aserbao on 2018 2018/4/4.8:42
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class Soul extends Material {

    private Material mMaterial;

    public Soul(Material material) {
        mMaterial = material;
    }

    @Override
    public int cost() {
        return 2 + mMaterial.cost();
    }

    @Override
    public String description() {
        return "加糖的" + mMaterial.description();
    }
}
