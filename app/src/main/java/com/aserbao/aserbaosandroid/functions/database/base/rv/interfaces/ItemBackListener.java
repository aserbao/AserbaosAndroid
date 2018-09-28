
package com.aserbao.aserbaosandroid.functions.database.base.rv.interfaces;

import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;

public interface ItemBackListener{
        void onItemClick(Thing s);
        void onItemLongClick(Thing s);
    }