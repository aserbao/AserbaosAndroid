package com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou;

import com.aserbao.aserbaosandroid.designMode.factoryMode.factory.RougamoFractory;

/**
 * Created by aserbao on 2018 2018/4/9.8:18
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public abstract class Rougamou {
    private Rougamou rougamou;
    private RougamoFractory mRougamoFractory;
    public void prepare(String type){
        mRougamoFractory.make(type);
    }
    abstract void cut();
    abstract void box();
}
