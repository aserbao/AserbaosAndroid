package com.aserbao.aserbaosandroid.functions.database.greenDao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aserbao.aserbaosandroid.functions.database.greenDao.MigrationHelper;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster.OpenHelper;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Student;

import org.greenrobot.greendao.database.Database;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/15
 * email: 1142803753@qq.com
 */
public class MyDaoMaster extends OpenHelper {
    private static final String TAG = "MyDaoMaster";
    public MyDaoMaster(Context context, String name) {
        super(context, name);
    }

    public MyDaoMaster(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },ThingDao.class);
        Log.e(TAG, "onUpgrade: " + oldVersion + " newVersion = " + newVersion);
    }
}
