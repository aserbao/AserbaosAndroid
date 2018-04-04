package com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee;

/**
 * Created by aserbao on 2018 2018/4/4.8:45
 * Email:aserbao@163.com
 * weiin: aserbao
 */

public class Cappuccio extends Material {
    @Override
    public int cost() {
        return 22;
    }

    @Override
    public String description() {
        return "卡布奇洛";
    }
}
