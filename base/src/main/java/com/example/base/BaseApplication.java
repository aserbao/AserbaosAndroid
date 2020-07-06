package com.example.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.danikula.videocache.HttpProxyCacheServer;
import com.example.base.database.MyDaoMaster;
import com.example.base.database.greendao.db.DaoMaster;
import com.example.base.database.greendao.db.DaoSession;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/22
 * @project: AserbaosAndroid
 * @package: com.example.base
 */
public class BaseApplication extends MultiDexApplication {
    public static int screenWidth,screenHeight;
    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        instance = this;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        initGreenDao();
    }

    //========================== 缓存区
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy() {
        BaseApplication app = instance;
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    public static final String DB_NAME ="aserbaos.db";
    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {

       /* File databasePath = getDatabasePath("aserbaos.db");
        boolean exists = databasePath.exists();
        File file = new File(databasePath.getParentFile(), "aserbaos.db");
        databasePath.renameTo(file);
        Log.e("test", "initGreenDao: " + databasePath.getAbsolutePath());*/
        MyDaoMaster helper = new MyDaoMaster(this, DB_NAME);
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
//        Database db = helper.getEncryptedWritableDb("aserbao");
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
