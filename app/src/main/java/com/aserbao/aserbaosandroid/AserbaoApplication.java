package com.aserbao.aserbaosandroid;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;

/**
 * Created by aserbao on 2018 2018/1/15.23:27
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class AserbaoApplication extends Application {

    private DaoSession daoSession;

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
     * 初始化GreenDao
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
