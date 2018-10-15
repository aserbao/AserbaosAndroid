package com.aserbao.aserbaosandroid;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.MyDaoMaster;

/**
 * Created by aserbao on 2018 2018/1/15.23:27
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class AserbaoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initData();
    }

    private void initData() {
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        MyDaoMaster helper = new MyDaoMaster(this, "aserbao.db");
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
