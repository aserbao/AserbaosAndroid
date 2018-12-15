package com.aserbao.aserbaosandroid;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.MyDaoMaster;

import org.greenrobot.greendao.database.Database;

import java.io.File;

/**
 * Created by aserbao on 2018 2018/1/15.23:27
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class AserbaoApplication extends Application {
    public static int screenWidth,screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initData();
    }

    private void initData() {
        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {

       /* File databasePath = getDatabasePath("aserbaos.db");
        boolean exists = databasePath.exists();
        File file = new File(databasePath.getParentFile(), "aserbaos.db");
        databasePath.renameTo(file);
        Log.e("test", "initGreenDao: " + databasePath.getAbsolutePath());*/
        MyDaoMaster helper = new MyDaoMaster(this, "aserbaos.db");
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
