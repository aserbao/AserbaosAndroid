package com.aserbao.aserbaosandroid.designMode.factoryMode.factory;

import com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou.Chongqing;
import com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou.Hunan;
import com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou.Rougamou;
import com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou.SiChuang;

/**
 * Created by aserbao on 2018 2018/4/8.22:03
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class RougamoFractory {

    public Rougamou make(String type){
        switch (type){
            case "Sichuang":
                return new SiChuang();
            case "Chongqing":
                return new Chongqing();
            case "Hunan":
                return new Hunan();
            default:
                return null;
        }
    }
}
