package com.aserbao.aserbaosandroid.designMode.factoryMode;

import com.aserbao.aserbaosandroid.designMode.factoryMode.factory.RougamoFractory;
import com.aserbao.aserbaosandroid.designMode.factoryMode.rougamou.Rougamou;

/**
 * Created by aserbao on 2018 2018/4/8.22:04
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class RougamoStore {
    RougamoFractory mRougamoFractory;

    public RougamoStore(RougamoFractory rougamoFractory) {
        mRougamoFractory = rougamoFractory;
    }

    public void createRougamo(String style){
        Rougamou rougamou = mRougamoFractory.make(style);
        rougamou.prepare();
    }


}
