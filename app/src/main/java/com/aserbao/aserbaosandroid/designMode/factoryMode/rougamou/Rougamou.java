package com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou;

/**
 * Created by aserbao on 2018 2018/4/9.8:18
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public abstract class Rougamou {
    private Rougamou rougamou;
    public void prepare(String type){
        if(type.equals("Sichuang")){
            rougamou = new SiChuang();
        /*}else if(type.equals("Chongqing")){
            rougamou = new Chongqing();*/
        }else if(type.equals("Hunan")){
            rougamou = new Hunan();
        }
    }
    abstract void cut();
    abstract void box();
}
