package com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou;

/**
 * Created by aserbao on 2018 2018/4/9.8:18
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public abstract class Rougamou {
    public void prepare(){
        System.out.println("Rougamou is prepareing");
        cut();
        box();
    }
    abstract void cut();
    abstract void box();
}
